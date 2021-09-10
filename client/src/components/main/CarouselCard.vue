<template>
  <div class="card">
    <img :src="image" alt="image"/>
    <span>{{name}}</span>
    <p>{{text}}</p>
  </div>
</template>

<script>
export default {
  name: 'CarouselCard',
  props: ['name', 'image', 'text'],
}
</script>

<style scoped>

.card {
  height: 34rem;
  width: 25rem;
  align-items: center;
  justify-content: center;
  border-radius: 2rem;
  background-color: #fff;
  box-shadow: 0 3px 20px 0 rgba(50, 50, 50, 0.3);
  transition: all .3s ease-in;
  overflow: hidden;
}

.card img {
  object-fit: cover;
  height: 18rem;
  width: 100%;
  display: block;
}

.card span {
  display: block;
  margin: 3rem 1rem 1.5rem;
  font-family: Montserrat, sans-serif;
  font-size: 1.5em;
  font-weight: 600;
  text-align: center;
}

.card p {
  margin: auto 1rem;
  font-family: Montserrat, sans-serif;
  font-size: 1em;
  font-weight: normal;
  text-align: center;
}
</style>