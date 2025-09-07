<script>
  import { onMount } from "svelte";

  let dispenserId = "";
  let name = "";
  let age = "";
  let gender = "";

  onMount(() => {
    // find the last used dispenserId
    const lastId = localStorage.getItem("lastDispenserId");

    if (lastId) {
      dispenserId = lastId;
      const saved = localStorage.getItem(`dispenser-${dispenserId}`);
      if (saved) {
        const data = JSON.parse(saved);
        name = data.name;
        age = data.age;
        gender = data.gender;
      }
    }
  });
</script>

<style>
  .profile-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: var(--color-background);
  }

  .profile-card {
    background: var(--color-card);
    padding: 2rem;
    border-radius: 1rem;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    width: 400px;
    animation: fadeIn 0.4s ease;
  }

  h2 {
    text-align: center;
    color: var(--color-primary);
    margin-bottom: 1.5rem;
  }

  p {
    font-size: 1rem;
    margin: 0.5rem 0;
    color: var(--color-text);
  }

  @keyframes fadeIn {
    from { opacity: 0; transform: translateY(10px); }
    to { opacity: 1; transform: translateY(0); }
  }
</style>

<div class="profile-container">
  <div class="profile-card">
    <h2>Profile</h2>
    {#if dispenserId}
      <p><strong>Dispenser ID:</strong> {dispenserId}</p>
      <p><strong>Name:</strong> {name}</p>
      <p><strong>Age:</strong> {age}</p>
      <p><strong>Gender:</strong> {gender}</p>
    {:else}
      <p>No profile data found. Please log in first.</p>
    {/if}
  </div>
</div>
