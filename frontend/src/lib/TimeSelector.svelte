<script>
  import Clock from './Clock.svelte';
  import PillDataContainer from './PillDataContainer.svelte';
  import DateNavigator from './DateNavigator.svelte';
  
  import { getTimings } from './functions';
  import { updateReminderTime } from './functions';

  let dateNav;
  let clock;
  let pillData;
  let clicked = $state(false);

  function handleClick() {
    clicked = true;
    setTimeout(() => (clicked = false), 150);
  }

  import { createEventDispatcher } from 'svelte';
  const dispatch = createEventDispatcher();

  let selected = $state("morning");
  const times = ["morning", "evening", "night"];

  function select(time) {
    selected = time;
    dispatch('select', { time });
  }
</script>
<DateNavigator bind:this={dateNav} />

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
<PillDataContainer bind:this={pillData}/>

<button 
  class="save-btn floating-save" 
  class:clicked={clicked} 
  onclick={() => {
    handleClick();
    let timeFormat = clock.formatTime();
    updateReminderTime(`${selected}_time`, timeFormat);
    pillData.getPillData("11-09-2002", selected);
  }}>
  Save
</button>

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

  .save-btn {
  background: #ffb85c;
  color: #fff;
  font-weight: bold;
  font-size: 1.2rem;
  border: none;
  border-radius: 2rem;
  padding: 0.5rem 2rem;
  cursor: pointer;
  box-shadow: none;
  transition: background 0.2s, transform 0.15s;
  letter-spacing: 0.02em;
}

.save-btn:hover {
  background: #ffa940;
}

.floating-save {
  position: fixed;
  right: 2rem;
  bottom: 5rem;
  z-index: 100;
}

.save-btn.clicked {
  transform: scale(0.95) translateY(4px);
}
</style>