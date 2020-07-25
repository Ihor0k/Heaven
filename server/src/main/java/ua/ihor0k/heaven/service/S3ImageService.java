package ua.ihor0k.heaven.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile("image-s3")
public class S3ImageService extends AbstractImageService {
    @Value("${aws.s3.bucket}")
    private String bucket;
    private AmazonS3 amazonS3;

    @Override
    protected void save(String fileName, Resource image) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.contentLength());
        metadata.setContentType(MediaType.IMAGE_PNG_VALUE);
        amazonS3.putObject(bucket, fileName, image.getInputStream(), metadata);
    }

    @Override
    protected boolean isFileExists(String fileName) {
        return amazonS3.doesObjectExist(bucket, fileName);
    }

    @Override
    public Resource download(String name) {
        S3Object object = amazonS3.getObject(bucket, name);
        S3ObjectInputStream inputStream = object.getObjectContent();
        return new InputStreamResource(new S3AbortOnCloseInputStream(inputStream)) {
            @Override
            public long contentLength() {
                return object.getObjectMetadata().getContentLength();
            }
        };
    }

    @Override
    public void delete(String name) {
        amazonS3.deleteObject(bucket, name);
    }

    @Autowired
    public void setAmazonS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

}
