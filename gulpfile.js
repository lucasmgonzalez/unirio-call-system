const gulp = require("gulp");
const browserify = require("browserify");
const babel = require("babelify");
const sass = require("gulp-sass");
const plumber = require("gulp-plumber");
const notify = require("gulp-notify");
const sourcemaps = require("gulp-sourcemaps");
const livereload = require('gulp-livereload');
const source = require("vinyl-source-stream");
const buffer = require("vinyl-buffer");

const config = {
    sass: {
      path: './src/main/resources/scss/',
      entrypoint: "styles.scss",
      export: "./src/main/webapp/styles/"
    },
    js: {
      path: "./src/main/resources/js/",
      entrypoint: "app.js",
      export: "./src/main/webapp/scripts/"
    }
  };

gulp.task("css", () =>
  gulp
    .src(config.sass.path + config.sass.entrypoint)
    .pipe(sass())
    .pipe(gulp.dest(config.sass.export))
);

gulp.task("js", () =>
  browserify(config.js.path + config.js.entrypoint, { debug: true })
    .transform(babel)
    .bundle()
    .on("error", err => console.error(err))
    .pipe(source("app.js"))
    .pipe(buffer())
    .pipe(gulp.dest(config.js.export))
);

gulp.task('build', ['css', 'js']);

gulp.task('watch', ['css', 'js'], () => {
  livereload.listen();
  gulp.watch([
    config.sass.path + '**',
  ], ['css']);
  gulp.watch([
    config.js.path + '**',
  ], ['js']);
});

gulp.task('default', ['watch']);
