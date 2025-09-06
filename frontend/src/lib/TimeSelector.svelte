<script>
  import Clock from './Clock.svelte';
  import { getTimings } from './functions';

  let clock;

  import { createEventDispatcher } from 'svelte';
  const dispatch = createEventDispatcher();

  let selected = $state("morning");
  const times = ["morning", "evening", "night"];

  function select(time) {
    selected = time;
    dispatch('select', { time });
  }
</script>

<div class="time-selector">
  {#each times as time}
    <button
      class:selected={selected === time}
      onclick={() => {
        select(time);
        getTimings().then(data => {
          clock.updateTime(data);
        });
        }}
      type="button"
    >
      {time}
    </button>
  {/each}
</div>

<Clock bind:this={clock} time={selected}/>

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