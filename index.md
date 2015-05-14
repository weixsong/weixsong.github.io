---
layout: page
title: Wei SONG's blog
---
{% include JB/setup %}

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
      <img class="first-slide" src="{{ site.url }}/assets/index_page/slide-new-img-cover.jpg" alt="First slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>Example headline.</h1>
          <p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p>
          <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="second-slide" src="{{ site.url }}/assets/index_page/2.jpg" alt="Second slide">
      <div class="container">
        <div class="carousel-caption">
          <h1>Another example headline.</h1>
          <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
          <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
        </div>
      </div>
    </div>
    <div class="item">
      <img class="third-slide" src="{{ site.url }}/assets/index_page/3.jpg" alt="Third slide">
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
</div><!-- /.carousel -->

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->
<div class="container marketing">

  <!-- Three columns of text below the carousel -->
  <div class="row">
    <div class="col-lg-4">
      <img class="img-circle" src="{{ site.url }}/assets/index_page/OCR.jpg" alt="ocr demo image" width="140" height="140">
      <h2>OCR Demo</h2>
      <p>Android OCR Demo is a simple Android app that shows how to do OCR on Android platform. You could follow this simple example and develop your non-trivial OCR Android app.
      This OCR demo use Tess-two as OCR library then we don't need to care about native code.</p>
      <p><a class="btn btn-default" href="https://github.com/weixsong/libra" target="_blank" role="button">View details &raquo;</a></p>
    </div><!-- /.col-lg-4 -->

    <div class="col-lg-4">
      <img class="img-circle" src="{{ site.url }}/assets/index_page/badge_PA200px.jpg" alt="working hard image" width="140" height="140">
      <h2>NDK Demo</h2>
      <p>Android NDK demo is a simple Android app that shows you how to develop native code by NDK, and it also shows you how to compile your native code, how to use native code in Android project, how to pass parameters between java and native c/c++ code.</p>
      <p><a class="btn btn-default" href="https://github.com/weixsong/libra" target="_blank" role="button">View details &raquo;</a></p>
    </div><!-- /.col-lg-4 -->

    <div class="col-lg-4">
      <img class="img-circle" src="{{ site.url }}/assets/index_page/badge_QA200px.jpg" alt="Coding hard image" width="140" height="140">
      <h2>ASM Demo</h2>
      <p>Android ASM demo is a relative complex Android app that shows you how to face detection on Android platform by OpenCV, how to develop native code by NDK, how to do ASM landmarks location by Stasm package, it also shows how to do these jobs efficiently by Thread and AsyncTask.</p>
      <p><a class="btn btn-default" href="https://github.com/weixsong/libra" target="_blank" role="button">View details &raquo;</a></p>
    </div><!-- /.col-lg-4 -->
  </div><!-- /.row -->

  <!-- START THE FEATURETTES -->
  <hr class="featurette-divider">

  <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">First featurette heading. <span class="text-muted">It'll blow your mind.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <!-- /END THE FEATURETTES -->
</div><!-- /.container -->

<div class="page-header">
  <h1>{{ page.title }} <small></small></h1>
</div>

Read [Jekyll Quick Start](http://jekyllbootstrap.com/usage/jekyll-quick-start.html)

Complete usage and documentation available at: [Jekyll Bootstrap](http://jekyllbootstrap.com)

## Update Author Attributes

In `_config.yml` remember to specify your own data:

    title : My Blog =)

    author :
      name : Name Lastname
      email : blah@email.test
      github : username
      twitter : username

The theme should reference these variables whenever needed.
    
## Sample Posts

This blog contains sample posts which help stage pages and blog data.
When you don't need the samples anymore just delete the `_posts/core-samples` folder.

    $ rm -rf _posts/core-samples

Here's a sample "posts list".

<ul class="posts">
  {% for post in site.posts %}
    <li><span>{{ post.date | date_to_string }}</span> &raquo; <a href="{{ BASE_PATH }}{{ post.url }}">{{ post.title }}</a></li>
  {% endfor %}
</ul>

