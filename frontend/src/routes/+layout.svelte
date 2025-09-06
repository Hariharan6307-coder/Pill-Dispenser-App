<script>
  import favicon from "$lib/assets/favicon.svg";
  import "../app.css";
  import NavBar from "$lib/NavBar.svelte";

  let { children } = $props();
</script>

<svelte:head>
  <link rel="icon" href={favicon} />
</svelte:head>

<main class="content">
  {@render children?.()}
</main>

<NavBar />

<style>
  .content {
    position: relative;
    min-height: 100vh;
    padding-bottom: 70px; /* same as navbar height */
    box-sizing: border-box;
  }
</style>
