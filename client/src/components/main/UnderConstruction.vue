<template>
  <div class="under-construction">
    <div class="header">
      <router-link to="/" class="link">
        <img class="icon-back" src="@/assets/icon_back.svg" alt="icon_back">
        <span>To the main page</span>
      </router-link>
    </div>
    <div class="body">
      <span>Sorry, the website is under construction.</span>
      <span>Updates are coming soon. </span>
    </div>
    <Footer/>
  </div>
</template>

<script>
import Footer from './Footer'

export default {
  name: 'UnderConstruction',
  components: {Footer}
}
</script>

<style scoped>

.under-construction {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}

.header {
  background-color: #f7fafd;
  height: 9em;
  line-height: 9em;
  background-size: 134px 80px;;
  background-position:center;
  background-image: url("~@/assets/logo_small.png");
  background-repeat: no-repeat;
}

.icon-back {
  margin-right: 1rem;
}

.body {
  background: url("~@/assets/under_construction.jpg") right center no-repeat;
  height: 100%;
  padding: 10rem 7rem;
}

.body span {
  display: block;
  font-family: Oswald, sans-serif;
  font-weight: normal;
  font-size: 3em;
  letter-spacing: -0.5px;
  color: #fff;
  white-space: nowrap;
}

.link {
  font-family: Montserrat, sans-serif;
  font-size: 1em;
  font-weight: normal;
  text-align: center;
  text-decoration: none;
  margin-left: 7rem;
}

</style>