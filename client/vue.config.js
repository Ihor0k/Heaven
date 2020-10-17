module.exports = {
    publicPath: './',
    pages: {
        admin: {
            entry: 'src/admin.js',
            template: 'public/index.html',
            filename: 'admin',
            title: 'Admin panel',
            chunks: ['chunk-vendors', 'chunk-common', 'admin']
        }
    },
    devServer: {
        proxy: 'http://localhost:8081'
    }
}