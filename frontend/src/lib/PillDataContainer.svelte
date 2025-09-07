<script>
  let pills = [
    { name: '', quantity: '' },
    { name: '', quantity: '' },
    { name: '', quantity: '' }
  ];
</script>

<div class="pill-table">
  {#each pills as pill, i}
    <div class="pill-row">
      <input
        type="text"
        placeholder="Pill Name"
        bind:value={pills[i].name}
        class="pill-name-input"
      />
      <input
        type="number"
        min="0"
        placeholder="Quantity"
        bind:value={pills[i].quantity}
        class="pill-qty-input"
      />
    </div>
  {/each}
</div>

<style>
.pill-table {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  width: 100%;
  max-width: 20rem;
  margin: 2rem auto;
}

.pill-row {
  display: flex;
  gap: 0.5rem;
}

.pill-name-input {
  flex: 1;
  padding: 0.5rem;
  border-radius: 0.5rem;
  border: 1px solid #ccc;
  font-size: 1rem;
}

.pill-qty-input {
  width: 5rem;
  padding: 0.5rem;
  border-radius: 0.5rem;
  border: 1px solid #ccc;
  font-size: 1rem;
}
</style>