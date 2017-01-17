'use strict';

var gulp = require('gulp');
var clean = require('gulp-clean');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var filter = require('gulp-filter');
var minifyCss = require('gulp-minify-css');
var gulpif = require('gulp-if');
var del = require('del');
var useref = require('gulp-useref');
/*var imagemin = require('gulp-imagemin');
 var pngquant = require('imagemin-pngquant');*/
var ngHtml2Js = require('gulp-ng-html2js');
var minifyHtml = require('gulp-minify-html');
var less = require('gulp-less');
var rev = require('gulp-rev');
var revReplace = require('gulp-rev-replace');
var rename = require('gulp-rename');
var notify = require('gulp-notify');

var dist = './dist';
var master = {
    template: {
        source: './master/template/**/*.html',
        dest: './dist/master/template'
    },
    img: {
        source: ['./master/img/*.{png,jpg,gif,ico}', './master/img/**/*.{png,jpg,gif,ico}'],
        dest: './dist/master/img'
    },
    fonts: {
        source: ['./lib/fonts/*.*'],
        dest: './dist/master/fonts'
    },
    dist: './dist/master'
};

gulp.task('clean', function () {
    return gulp.src(['./dist'])
        .pipe(clean());
})

//深度压缩图片
gulp.task('master:img', function () {
    return gulp.src(master.img.source)
        /*.pipe(imagemin({
         progressive: true,
         svgoPlugins: [{removeViewBox: false}],
         use: [pngquant()]
         }))*/
        .pipe(gulp.dest(master.img.dest))
        .pipe(notify({message: 'master.img task ok'}));
});

//移动fonts
gulp.task('master:fonts', function () {
    return gulp.src(master.fonts.source)
        .pipe(gulp.dest(master.fonts.dest))
        .pipe(notify({message: 'master.fonts task ok'}));
});

// 移动模版html
gulp.task('master:template', function () {
    return gulp.src(master.template.source)
        .pipe(gulp.dest(master.template.dest))
        .pipe(notify({message: 'master.template html task ok'}));

});

// index压缩js,css
gulp.task('master:build', ['master:img', 'master:fonts', 'master:template'], function () {
    var assets = useref.assets();

    gulp.src(['authcallback.html'])
        .pipe(gulp.dest(dist))
        .pipe(notify({message: 'authcallback build task ok'}));


    return gulp.src(['index.html'])
        .pipe(assets)
        .pipe(gulpif('*.js', uglify()))
        .pipe(gulpif('*.css', minifyCss({
            advanced: false,//类型：Boolean 默认：true [是否开启高级优化（合并选择器等）]
            compatibility: 'ie7'//保留ie7及以下兼容写法 类型：String 默认：''or'*' [启用兼容模式； 'ie7'：IE7兼容模式，'ie8'：IE8兼容模式，'*'：IE9+兼容模式]
        })))
        .pipe(assets.restore())
        .pipe(useref())
        .pipe(gulp.dest(dist))
        .pipe(notify({message: 'master.template html task ok'}));

});


var open5 = {
    template: ['./open5/tpl/**/*.html'],
    less: ['./open5/css/less/*.less'],
    fonts: ['./open5/fonts/**/*.{ttf,woff,eot,svg}'],
    images: ['./open5//images/**/*.{png,jpg,gif}'],
    dist: ['./dist/open5']
};

gulp.task('open5:build', ['open5:template'], function () {
    var assets = useref.assets();

    gulp.src(open5.fonts)
        .pipe(gulp.dest('./dist/open5/fonts'));

    gulp.src(open5.images)
        .pipe(gulp.dest('./dist/open5/images'));

    return gulp.src(['./open5/publish.html', './open5/editor.html', './open5/share.html','./open5/editpreview.html'])
        .pipe(assets)
        .pipe(gulpif('*.js', uglify()))
        .pipe(gulpif('*.css', minifyCss({
            advanced: false,//类型：Boolean 默认：true [是否开启高级优化（合并选择器等）]
            compatibility: 'ie7'//保留ie7及以下兼容写法 类型：String 默认：''or'*' [启用兼容模式； 'ie7'：IE7兼容模式，'ie8'：IE8兼容模式，'*'：IE9+兼容模式]
        })))
        .pipe(assets.restore())
        .pipe(useref())
        .pipe(gulp.dest('./dist/open5'))
        .pipe(notify({message: 'open5.build build task ok'}));
});

gulp.task('open5:less', function () {
    return gulp.src(['./open5/css/less/app.less', './open5/css/less/player.less'])
        .pipe(less({
            paths: ['./open5/css/less']
        }))
        .pipe(gulp.dest('./open5/css'))
        .pipe(notify({message: 'open5.less less task ok'}));
});

gulp.task('open5:template', function () {

    return gulp.src(open5.template)
        .pipe(gulp.dest('./dist/open5/tpl'))
        .pipe(notify({message: 'master.template html task ok'}));
    /*return gulp.src(open5.template)
     .pipe(minifyHtml({
     empty: true,
     spare: true,
     quotes: true
     }))
     .pipe(ngHtml2Js({
     moduleName: 'app.tpls',
     prefix: 'tpl/'
     }))
     .pipe(concat('tpls.min.js'))
     .pipe(uglify())
     .pipe(gulp.dest('./open5/js'))
     .pipe(notify({message: 'open5.template html task ok'}));*/
});


gulp.task('master:revision', ['master:build'], function () {
    return gulp.src(['./dist/master/**/*.css', './dist/master/**/*.js'])
        .pipe(rev())
        .pipe(gulp.dest('./dist/master'))
        .pipe(rev.manifest())
        .pipe(gulp.dest('./dist/master'));
});

gulp.task('master:revreplace', ['master:revision'], function () {
    var manifest = gulp.src('./dist/master/rev-manifest.json');

    return gulp.src(['./dist/index.html'])
        .pipe(revReplace({manifest: manifest}))
        .pipe(gulp.dest('./dist'));
});


gulp.task('open5:revision', ['open5:build'], function () {
    return gulp.src(['./dist/open5/**/*.css', './dist/open5/**/*.js'])
        .pipe(rev())
        .pipe(gulp.dest('./dist/open5'))
        .pipe(rev.manifest())
        .pipe(gulp.dest('./dist/open5'));
});

gulp.task('open5:revreplace', ['open5:revision'], function () {
    var manifest = gulp.src('./dist/open5/rev-manifest.json');

    return gulp.src(['./dist/open5/editor.html', './dist/open5/publish.html'])
        .pipe(revReplace({manifest: manifest}))
        .pipe(gulp.dest('./dist/open5'));
});

gulp.task('default', ['clean'], function () {
    gulp.start(
        'master:revreplace',
        'open5:revreplace',
        'favicon'
    );
});

gulp.task('favicon', function () {
    return gulp.src('./favicon.ico')
        .pipe(gulp.dest('./dist'));
});


/*gulp.task('copy', function() {
 gulp.src('./index.html')
 .pipe(rename('index.as'))
 .pipe(gulp.dest('./produtct'));
 });

 gulp.task('copy2',['copy'], function() {
 gulp.src('./produtct/index.as')
 .pipe(rename('index.as'))
 .pipe(gulp.dest('./'));
 });*/

