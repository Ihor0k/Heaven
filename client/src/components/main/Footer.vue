<template functional>
  <div class="footer">
    <img class="logo-line" src="@/assets/logo_line.png" alt="logo_line"/>
    <p class="section6-copyright">Copyright © 2021 Heaven Company. All rights reserved.</p>
  </div>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
  .footer {
    background-color: #f7fafd;
    height: 7.5rem;
    padding: 0 7.5rem;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
</style>