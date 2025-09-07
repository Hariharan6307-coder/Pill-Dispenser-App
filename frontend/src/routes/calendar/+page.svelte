<script>
  function flipFlap() {
    console.log("Flip the flap button clicked!");
  }

  function rotateBase() {
    console.log("Rotate the base button clicked!");
  }
</script>

<style>
  .calendar-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: var(--color-background);
  }

  .calendar-card {
    background: var(--color-card);
    padding: 2rem;
    border-radius: 1rem;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    width: 350px;
    text-align: center;
    animation: fadeIn 0.4s ease;
  }

  h2 {
    color: var(--color-primary);
    margin-bottom: 1.5rem;
  }

  button {
    display: block;
    width: 100%;
    margin: 0.75rem 0;
    padding: 0.9rem;
    font-size: 1rem;
    border: none;
    border-radius: 0.5rem;
    background: var(--color-primary);
    color: white;
    cursor: pointer;
    transition: background 0.2s;
  }

  button:hover {
    background: #007f8e;
  }

  @keyframes fadeIn {
    from { opacity: 0; transform: translateY(10px); }
    to { opacity: 1; transform: translateY(0); }
  }
</style>

<div class="calendar-container">
  <div class="calendar-card">
    <h2>Controls</h2>
    <button onclick={flipFlap}>Flip the flap</button>
    <button onclick={rotateBase}>Rotate the base</button>
  </div>
</div>
