module.exports = {
    publicPath: './',
    pages: {
        index: {
            entry: 'src/main.js',
            template: 'public/index.html',
            filename: 'index.html',
            title: 'Heaven',
            chunks: ['chunk-vendors', 'chunk-common', 'index']
        },
        admin: {
            entry: 'src/admin.js',
            template: 'public/index.html',
            filename: 'admin.html',
            title: 'Admin panel',
            chunks: ['chunk-vendors', 'chunk-common', 'admin']
        }
    },
    devServer: {
        proxy: 'http://localhost:8081'
    },
    runtimeCompiler: true
}