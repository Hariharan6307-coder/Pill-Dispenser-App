<script>
  import { get } from "svelte/store";
  import { getTimings } from "./functions";

  let { time } = $props();
  let selectedTime = $state(time);

  getTimings().then(data => updateTime(data));

  export function updateTime(data) {
    selectedTime = time;
    const timings = data[`${selectedTime}_time`];
    if (timings) {
      const [h, m, s] = timings.split(":").map(Number) ;
      hour = parseInt(h);
      minute = parseInt(m);
    }
  }

  let hour = $state(8);
  let minute = $state(0);

  function format(value) {
    return value.toString().padStart(2, "0");
  }

  function increaseHour() {
    hour = hour === 12 ? 1 : hour + 1;
  }

  function decreaseHour() {
    hour = hour === 1 ? 12 : hour - 1;
  }

  function increaseMinute() {
    minute = (minute + 1) % 60;
  }

  function decreaseMinute() {
    minute = (minute - 1 + 60) % 60;
  }
</script>

<!-- Wrapper to center -->
<div class="wrapper">
  <div class="clock-container">
    <!-- Hour -->
    <div class="time-box">
      <button onclick={increaseHour}>▲</button>
      <span>{format(hour)}</span>
      <button onclick={decreaseHour}>▼</button>
    </div>

    <span class="colon">:</span>

    <!-- Minute -->
    <div class="time-box">
      <button onclick={increaseMinute}>▲</button>
      <span>{format(minute)}</span>
      <button onclick={decreaseMinute}>▼</button>
    </div>
  </div>
</div>

<style>
  .wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 7rem
  }

  .clock-container {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    background: var(--color-primary);
    border-radius: 12px;
    padding: 0.5rem 1rem;
    color: #000;
    max-width: 20rem;
    width: 100%;
    height: 100%;
    justify-content: center;
  }

  .time-box {
    display: flex;
    flex-direction: column;
    align-items: center;
    background: var(--color-card);
    border-radius: 8px;
    padding: 0.3rem 0.6rem;
    font-size: 1.2rem;
    font-weight: bold;
    flex: 1;
  }

  .time-box span {
    margin: 0.2rem 0;
  }

  .colon {
    font-size: 1.2rem;
    font-weight: bold;
    color: var(--color-card);
  }

  button {
    background: none;
    border: none;
    cursor: pointer;
    font-size: 0.8rem;
    color: var(--color-primary);
  }

  .period {
    display: flex;
    flex-direction: column;
    margin-left: 0.5rem;
  }

  .period button {
    font-size: 0.8rem;
    font-weight: bold;
    color: var(--color-card);
    opacity: 0.6;
    transition: 0.2s;
  }

  .period button.active {
    opacity: 1;
    text-decoration: underline;
  }
</style>
