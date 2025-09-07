<script>
  import { updatePillData } from "./functions";
  import { fetchWeeklyPillData } from "./functions";

  let pills = [];

export async function loadWeeklyPills() {
  const result = await fetchWeeklyPillData();
  if (result.weekly_pill_data) {
    pills = result.weekly_pill_data; // assign parsed data
  } else {
    pills = []; // fallback if no data
  }
}

loadWeeklyPills();

  export function getPillData(date, selectedTime) {

    // This function can be expanded to fetch data from an API or local storage
    let pillData = [
          { name: pills[0].name, quantity: pills[0].quantity},
          { name: pills[1].name, quantity: pills[1].quantity },
          { name: pills[2].name, quantity: pills[2].quantity }
        ];

    updatePillData(JSON.stringify(pillData));
  } 
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
        placeholder="Qty"
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