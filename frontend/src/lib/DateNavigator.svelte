<script>
  // today's date
  const today = new Date();

  // stateful selected date (starts as today)
  let currentDate = $state(new Date(today));
  let maxDate = $state(new Date(today));
  maxDate.setDate(today.getDate() + 7);

  // derived states
  let isToday = $derived(currentDate.toDateString() === today.toDateString());
  let isMaxDay = $derived(currentDate.toDateString() === maxDate.toDateString());

  // helpers
  function formatDate(date) {
    return date.toLocaleDateString("en-GB", {
      day: "numeric",
      month: "long",
      year: "numeric"
    });
  }

  function goPrev() {
    if (!isToday) {
      currentDate = new Date(currentDate.setDate(currentDate.getDate() - 1));
    }
  }

  function goNext() {
    if (!isMaxDay) {
      currentDate = new Date(currentDate.setDate(currentDate.getDate() + 1));
    }
  }
</script>
<div class="date-container">
  <button onclick={goPrev} >
    <img src="/images/icons/arrow-icon.png" alt="Previous" class="icon left" />
  </button>

  <span class="date-text">{formatDate(currentDate)}</span>

  <button onclick={goNext} >
    <img src="/images/icons/arrow-icon.png" alt="Next" class="icon right" />
  </button>
</div>

<style>
  .date-container {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 1.5rem;
    padding: 0.75rem 1.25rem;
    border-radius: 8px;
  }

  .date-text {
    font-weight: bold;
    font-size: 1rem;
    color: var(--color-primary, teal);
  }

  button {
    border: 0;
    padding: 0.4rem;
    background: transparent;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: 0.2s ease;
  }

  button:disabled {
    opacity: 0.4;
    cursor: not-allowed;
  }

  .icon {
    width: 40px;
    height: 40px;
  }

  .left {
    transform: scaleX(-1);
  }
</style>
