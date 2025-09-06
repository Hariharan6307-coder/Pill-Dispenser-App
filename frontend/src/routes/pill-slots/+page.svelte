<script>
  import DateNavigator from "../../lib/DateNavigator.svelte";
  import TimeSelector from "../../lib/TimeSelector.svelte";
  import Clock from "../../lib/Clock.svelte";

  let clicked = false;

  function handleClick() {
    clicked = true;
    setTimeout(() => (clicked = false), 150);
  }
</script>

<DateNavigator/>
<TimeSelector />

<button 
  class="save-btn floating-save" 
  class:clicked={clicked} 
  on:click={handleClick}>
  Save
</button>

<style>
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