<template>
  <div class="app">
    <div id="section1">
      <div class="section1-left">
        <img class="logo" src="@/assets/logo.png" alt="logo">
        <h1>Create your own Paradise</h1>
        <p class="caption">We provide people with their dream place in Heaven</p>
        <router-link to="/constructor" class="button text-button">Get started!</router-link>
      </div>
      <div class="section1-right">
        <img class="section1-right-image" src="@/assets/illustration.png" alt="illustration">
      </div>
    </div>
    <div id="section2">
      <div class="section2-title">
        <h2>How it works</h2>
        <p class="caption">Have you ever thought about what is waiting for you THERE?</p>
        <p class="caption">We give you a possibility to book a place in Heaven and manage your future.</p>
      </div>
      <div class="section2-cards">
        <div class="section2-card" id="card-one">
          <div class="circle disable-selection">
            <span class="number">1</span>
          </div>
          <h3 class="card-title">Think</h3>
          <p class="card-text">Close your eyes for a moment and think of a place where you would rest in peace and joy.
            Where the security and confidence are your companions forever. Don’t hurry! Just relax.</p>
        </div>
        <img class="path" id="path-one" src="@/assets/path_1.png" alt="path_1">
        <div class="section2-card" id="card-two">
          <div class="circle disable-selection">
            <span class="number">2</span>
          </div>
          <h3 class="card-title">Choose</h3>
          <p class="card-text">Build your place in Heaven with our simple constructor. You can also add your own
            personal wishes and everything will be done in the best way before your arrival!</p>
        </div>
        <img class="path" id="path-two" src="@/assets/path_2.png" alt="path_2">
        <div class="section2-card" id="card-three">
          <div class="circle disable-selection">
            <span class="number">3</span>
          </div>
          <h3 class="card-title">Take!</h3>
          <p class="card-text">You will receive a special e-mail with a picture of the place you have booked in
            Paradise. We look forward to your arrival!</p>
          <router-link to="/constructor" class="section2-button button text-button">Get started!</router-link>
        </div>
      </div>
    </div>
    <div id="section3">
      <h3>Get on the board with us</h3>
      <p>Be the first of the lucky ones to be in the best place in Heaven.</p>
      <p>Create your dream place in three minutes with us!</p>
      <router-link to="/constructor" class="button text-button section3-button">Make an order</router-link>
    </div>
    <div id="section4">
      <h2>Why choose us</h2>
      <ul>
        <li class="section4-item">
          <div class="section4-item-image-wrapper">
            <img class="section4-item-image icon_privacy" src="@/assets/icon_privacy.png" alt="privacy"/>
          </div>
          <div class="section4-item-text-wrapper">
            <h3 class="section4-item-text-header">Privacy</h3>
            <p>We care about your personal data and we use most modern technologies to
              protect them!</p>
          </div>
        </li>
        <li class="section4-item">
          <div class="section4-item-image-wrapper">
            <img class="section4-item-image icon_reliability" src="@/assets/icon_reliability.png" alt="reliability"/>
          </div>
          <div class="section4-item-text-wrapper">
            <h3 class="section4-item-text-header">Reliability</h3>
            <p>We are the first company who started to provide people with such unique oportunity. With our many years
              of experience and the use of the newest technologies and professionalism of the staff, we guarantee an
              incredible experience and guaranteed quality services for all our customers regardless of religion and
              overall wealth.</p>
          </div>
        </li>
        <li class="section4-item">
          <div class="section4-item-image-wrapper">
            <img class="section4-item-image" src="@/assets/icon_simplicity.png" alt="simplicity"/>
          </div>
          <div class="section4-item-text-wrapper">
            <h3 class="section4-item-text-header">Simplicity</h3>
            <p>Easy and simple. Your dreams have never been so close!</p>
          </div>
        </li>
      </ul>
    </div>
    <div id="section5">
      <h2>What our clients say</h2>
      <Carousel class="section5-carousel" levels="2" scaling-factor="0.82" shift="0.64">
        <CarouselCard name="Bansilal Brata"
                      :image="require('@/assets/client_1.jpg')"
                      text="I am more than happy after making an order in the Heaven company! My wife and I are looking forward to leaving for our dream paradise!"/>
        <CarouselCard name="Ksenia Falk"
                      :image="require('@/assets/client_2.jpg')"
                      text="Great customer support from beginning to end of the process. The team is really interested in being as close as possible to your needs at every stage. I would recommend them unreservedly."/>
        <CarouselCard name="Fariba Mirzaii"
                      :image="require('@/assets/client_3.jpg')"
                      text="Fantastic organisation! Individual approach, quick reaction, exceeding your expectations. Special thanks to the managers for the wonderful job and professionalism!"/>
        <CarouselCard name="Alexander Christense"
                      :image="require('@/assets/client_4.jpg')"
                      text="My father told me to always care about my future and plan it. Thanks to Heaven Company team for their work and what they are doing for growing young generation. Think in advance and enjoy!"/>
      </Carousel>
      <router-link to="/constructor" class="button text-button">Make an order</router-link>
    </div>
    <Footer/>
  </div>
</template>

<script>
import axios from 'axios'
import Carousel from './Carousel'
import CarouselCard from './CarouselCard'
import Footer from './Footer'

axios.defaults.baseURL = '/api'

require('@/assets/styles/common.css')

export default {
  name: 'Main',
  components: {
    Carousel,
    CarouselCard,
    Footer
  }
}

</script>

<style scoped>

h1 {
  font-family: Oswald, sans-serif;
  font-weight: normal;
  font-size: 3em;
  letter-spacing: -0.5px;
  color: #172127;
  white-space: nowrap;
}

h2 {
  font-family: Oswald, sans-serif;
  font-weight: normal;
  font-size: 2.5em;
  letter-spacing: -0.5px;
  color: #172127;
  white-space: nowrap;
}

h3 {
  font-family: Oswald, sans-serif;
  font-weight: normal;
  font-size: 1.75em;
  color: #172127;
  white-space: nowrap;
}

p {
  font-family: 'PT Sans', sans-serif;
  font-size: 1em;
  color: #2d2d2d;
  opacity: 0.8;
  margin: 0;
}

.caption {
  margin: 0;
  font-size: 1.25em;
}

#section1 {
  background-color: #f2fbff;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  min-width: 80rem;
}

#section1 h1 {
  margin-top: 4.5rem;
  margin-bottom: 1.5rem;
}

#section1 .caption {
  margin-bottom: 2.5rem;
}

.section1-left {
  flex-shrink: 0;
  margin: auto;
  padding: 9rem 3rem;
}

.section1-right-image {
  max-width: 100%;
}

#section2 {
  margin: 8rem auto;
}

.section2-title {
  text-align: center;
  margin-bottom: 6rem;
}

.section2-title h2 {
  padding-bottom: 1.5rem;
}

.section2-cards {
  margin: auto;
  position: relative;
}

.section2-card {
  position: absolute;
  width: 30rem;
  height: 13rem;
  border: solid 3px #d6dee5;
  border-radius: 1rem;
  box-sizing: border-box;
  display: inline-block;
  vertical-align: top;
}

.circle {
  border-radius: 50%;
  width: 2.5rem;
  height: 2.5rem;
  margin: 1.5rem;
  background-color: #0eb4ee;
  display: inline-flex;
  justify-content: center;
  vertical-align: top;
}

.number {
  font-family: 'PT Sans', sans-serif;
  font-weight: bold;
  font-size: 1.5em;
  color: #fff;
  line-height: 2.5rem;
}

.card-title {
  margin-top: 1.5rem;
  line-height: 2.5rem;
  display: inline-block;
}

.card-text {
  margin: 0 2rem 1rem 5.5rem;
  line-height: 1.25;
}

.path {
  position: absolute;
  z-index: 1;
}

@media (min-width: 100rem) {
  .section2-cards {
    width: 94rem;
    height: 30rem;
  }

  #card-one {
    left: 0;
    top: 0;
  }

  #path-one {
    left: 27rem;
    top: 12rem;
  }

  #card-two {
    left: 32rem;
    top: 7.5rem;
  }

  #path-two {
    left: 61.5rem;
    top: 8.5rem;
  }

  #card-three {
    left: 64rem;
    top: 15rem;
  }
}

@media (min-width: 80rem) and (max-width: 99.99rem) {
  .section2-cards {
    width: 74rem;
    height: 45rem;
  }

  #card-one {
    left: 0;
    top: 0;
  }

  #path-one {
    transform: rotate(40deg);
    left: 14rem;
    top: 14rem;
  }

  #card-two {
    left: 20rem;
    top: 15rem;
  }

  #path-two {
    left: 49rem;
    top: 23rem;
  }

  #card-three {
    left: 44rem;
    top: 30rem;
  }
}


@media (max-width: 79.99rem) {
  .section2-cards {
    width: 30rem;
    height: 45rem;
  }

  .section2-card {
    left: 0;
  }

  #card-one {
    top: 0;
  }

  #path-one {
    transform: rotate(90deg) scale(0.7);
    left: -1rem;
    top: 11rem;
  }

  #card-two {
    top: 15rem;
  }

  #path-two {
    transform: rotate(45deg) scale(0.7);
    left: 19rem;
    top: 25rem;
  }

  #card-three {
    top: 30rem;
  }
}

.section2-button {
  position: absolute;
  display: block;
  white-space: nowrap;
  transform: translate(-50%, 50%);
  left: 50%;
  bottom: 0;
}

#section3 {
  padding: 3.5rem 0;
  background-color: #0eb4ee;
  color: #fff;
  text-align: center;
}

#section3 h3, #section3 p {
  color: #fff;
}

#section3 h3 {
  margin-bottom: 1rem;
}

.section3-button {
  border: solid 2px #fff;
  background-color: #0eb4ee;
  margin-top: 2rem;
}

.section3-button:hover {
  box-shadow: 0 0 0 1px #fff;
}

.section3-button:active {
  background-color: #fff;
  color: #172127;
}

#section4 {
  padding: 7.5rem 3rem;
  display: flex;
  gap: 3rem;
  justify-content: space-evenly;
}

.section4-item {
  white-space: nowrap;
}

.section4-item:not(:first-child) {
  border-top: 3px solid #f3f5f7;
  padding-top: 2rem;
}

.section4-item:not(:last-child) {
  padding-bottom: 2rem;
}

.section4-item-image-wrapper {
  vertical-align: middle;
  display: inline-block;
  width: 10rem;
  height: 10rem;
  background-color: #d3f2fe;
  border-radius: 50%;
  position: relative;
}

.section4-item-image {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  margin: auto;
}

.icon_privacy {
  margin-bottom: 1.5rem;
}

.icon_reliability {
  margin-bottom: 2rem;
}

.section4-item-text-wrapper {
  display: inline-block;
  vertical-align: middle;
  margin-left: 3rem;
  white-space: normal;
  max-width: 42rem;
}

.section4-item-text-header {
  margin-bottom: 1rem;
}

#section5 {
  background-image: url("~@/assets/section5_bg.svg");
  background-size: cover;
  padding: 10rem 0 7.5rem 0;
  text-align: center;
}

.section5-carousel {
  width: 100%;
  margin: 3rem auto 4rem;
  display: inline-block;
}

</style>

<style>
.button {
  background-color: #0eb4ee;
}

.button:hover {
  background-color: #34c6fa;
}

.button:active {
  background-color: #06a5dd;
}

.text-button {
  padding: 1rem 4rem 1rem 4rem;
  border-radius: 2rem;
  font-family: 'PT Sans', sans-serif;
  font-size: 1.5em;
  font-weight: bold;
  color: #fff;
}

</style>