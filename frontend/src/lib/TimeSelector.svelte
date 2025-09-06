<!-- TimeSelector.svelte -->
<script>
  let selected = $state("morning");
  const times = ["morning", "evening", "night"];

  function select(time) {
    selected = time;
    // Optionally, dispatch an event if you want to notify parent
    // createEventDispatcher() can be used for that
  }
</script>

<div class="time-selector">
  {#each times as time}
    <button
      class:selected={selected === time}
      onclick={() => select(time)}
      type="button"
    >
      {time}
    </button>
  {/each}
</div>

<style>
  .time-selector {
    display: flex;
    gap: 1rem;
    justify-content: center;
    margin: 1rem 0;
  }
  button {
    border: 2px solid #16a2ad;
    background: transparent;
    color: #16a2ad;
    font-weight: bold;
    border-radius: 2rem;
    padding: 0.4rem 1.2rem;
    cursor: pointer;
    transition: background 0.2s, color 0.2s;
    text-transform: capitalize;
  }
  button.selected {
    background: #16a2ad;
    color: #fff;
  }
</style>