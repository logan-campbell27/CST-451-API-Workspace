<template>
    <nav>
    <a href="ComparePlayer.vue">ComparePlayer.vue</a>
  </nav>

</template>

export default {
    name: "Navigation"
}
<style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }

    nav {
      background-color: #333;
      overflow: hidden;
    }

    nav a {
      float: left;
      display: block;
      color: white;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
    }

    nav a:hover {
      background-color: #ddd;
      color: black;
    }
  </style>