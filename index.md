---
layout: default
title: Wei SONG's blog
---

<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="first-slide" src="{{ BASE_PATH }}/assets/index_page/slide-new-img-cover.jpg" alt="First slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>Example headline.</h1>
          <p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p>
          <p><a class="btn btn-lg btn-primary" href="{{ BASE_PATH }}/assets/elasticlunr/docs/index.html" role="button">Read documents</a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="second-slide" src="{{ BASE_PATH }}/assets/index_page/2.jpg" alt="Second slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>Another example headline.</h1>
          <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
          <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="third-slide" src="{{ BASE_PATH }}/assets/index_page/3.jpg" alt="Third slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>One more for good measure.</h1>
          <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
          <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
        </div>
      </div>
    </div>
  </div>
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->
<div class="container marketing">

  <!-- Three columns of text below the carousel -->
  <div class="row">
    <div class="col-lg-4">
      <img class="img-circle" src="{{ BASE_PATH }}/assets/index_page/OCR.jpg" alt="ocr demo image" width="140" height="140">
      <h2>OCR Demo</h2>
      <p>Android OCR Demo is a simple Android app that shows how to do OCR on Android platform. You could follow this simple example and develop your non-trivial OCR Android app.
      This OCR demo use Tess-two as OCR library then we don't need to care about native code.</p>
      <p><a class="btn btn-default" href="https://github.com/weixsong/libra#ocrdemo" target="_blank" role="button">View details &raquo;</a></p>
    </div><!-- /.col-lg-4 -->

    <div class="col-lg-4">
      <img class="img-circle" src="{{ BASE_PATH }}/assets/index_page/badge_PA200px.jpg" alt="working hard image" width="140" height="140">
      <h2>NDK Demo</h2>
      <p>Android NDK demo is a simple Android app that shows you how to develop native code by NDK, and it also shows you how to compile your native code, how to use native code in Android project, how to pass parameters between java and native c/c++ code.</p>
      <p><a class="btn btn-default" href="https://github.com/weixsong/libra#ndkdemo" target="_blank" role="button">View details &raquo;</a></p>
    </div><!-- /.col-lg-4 -->

    <div class="col-lg-4">
      <img class="img-circle" src="{{ BASE_PATH }}/assets/index_page/badge_QA200px.jpg" alt="Coding hard image" width="140" height="140">
      <h2>ASM Demo</h2>
      <p>Android ASM demo is a relative complex Android app that shows you how to face detection on Android platform by OpenCV, how to develop native code by NDK, how to do ASM landmarks location by Stasm package, it also shows how to do these jobs efficiently by Thread and AsyncTask.</p>
      <p><a class="btn btn-default" href="https://github.com/weixsong/libra#asmdemo" target="_blank" role="button">View details &raquo;</a></p>
    </div><!-- /.col-lg-4 -->
  </div><!-- /.row -->

  <div class='ll'>
    <div class="col-md-12" style="margin-left: -15px;margin-bottom: 30px; padding-bottom: 15px; border-bottom:1px solid rgba(7,94,115,0.3);"></div>
  </div>

  <!-- START THE FEATURETTES -->

  <div class="row featurette">
    <div class="col-md-7">
      <h2 class="featurette-heading">Android OCR Demo</h2>
      <h3>Simple app shows you how to do OCR on Android platform</h3>
      <p class="lead">1. This project use <a href="https://github.com/weixsong/tess-two">tess-two</a> project to do OCR, using tess-two project make it simple to develop OCR Android project, you don't need to do any native coding and compiling work, just reference this project and use it as java package.</p>
      <p class="lead">2. Currently this Android OCR Demo support 2 languages: English and Chinese, effect for English is better than Chinese. If you want to do other language OCR, please goto <a href="https://code.google.com/p/tesseract-ocr/">tesseract-ocr</a> project and download the trained model for your specific language, or you could train your OCR model yourself, but model training is non-trivial task.</p>
      <p class="lead">3. Alought OCR is developed by c, by using <a href="https://github.com/weixsong/tess-two">tess-two</a> project, you don't need to care about Android hardware platform (armeabi, armeabi-v7a, mips, x86), usually for NDK development, hardware platform is really trivial thing you need to care about.</p>
      <p><a class="btn btn-default" href="https://github.com/weixsong/libra#ocrdemo" target="_blank" role="button">View details &raquo;</a></p>
    </div>
    <div class="col-md-5">
      <img class="featurette-image img-responsive center-block" src="{{ site.url }}/assets/index_page/ocr_app_demo.png" alt="ocr demo image">
    </div>
  </div>

  <div class="col-md-12" style="margin-left: -15px;margin-bottom: 30px; padding-bottom: 15px; border-bottom:1px solid rgba(7,94,115,0.3);"></div>

  <div class="row featurette">
    <div class="col-md-7 col-md-push-5">
      <h2 class="featurette-heading">Android NDK Demo</h2>
      <h3>Simple app shows you how to develop native c/c++ by NDK on Android platform</h3>
      <p class="lead">1. This project is really a simple demo project that shows you how to configure native code development and how to develop native code in c/c++, how to compile native code by NDK, how to call native code in Android project by JNI.</p>
      <p class="lead">2. The main purpose of this project is give you an example that showing how to configure NDK development environment. Please goto <a href="https://github.com/weixsong/libra#ndkdemo">NDK Demo</a> to see the detailed setup instructions.</p>
      <p class="lead">3. In order to develop your customized Android app by native code, you need to understand basic mechanism of JNI.</p>
      <p><a class="btn btn-default" href="https://github.com/weixsong/libra" target="_blank" role="button">View details &raquo;</a></p>
    </div>
    <div class="col-md-5 col-md-pull-7">
      <img class="featurette-image img-responsive center-block" src="{{ BASE_PATH }}/assets/index_page/ndk_demo.png" alt="android ndk develop image">
    </div>
  </div>
  <!-- /END THE FEATURETTES -->
  <div class="col-md-12" style="margin-left: -15px;margin-bottom: 30px; padding-bottom: 15px; border-bottom:1px solid rgba(7,94,115,0.3);"></div>
</div>

