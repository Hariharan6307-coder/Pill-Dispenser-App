<script>
  import { goto } from "$app/navigation"; // only needed if SvelteKit

  let step = $state(1);

  let dispenserId = $state("");
  let name = $state("");
  let age = $state("");
  let gender = $state("");

  let isExisting = $state(false);

  function handleDispenserSubmit() {
    if (!dispenserId.trim()) {
      alert("Please enter Dispenser ID");
      return;
    }

    const saved = localStorage.getItem(`dispenser-${dispenserId}`);
    if (saved) {
      const data = JSON.parse(saved);
      name = data.name;
      age = data.age;
      gender = data.gender;
      isExisting = true;
      step = 2; 
    } else {
      name = "";
      age = "";
      gender = "";
      isExisting = false;
      step = 3; 
    }
  }

  function handleUserSubmit() {
  if (!name.trim() || !age || !gender) {
    alert("Please fill all details");
    return;
  }

  localStorage.setItem(
    `dispenser-${dispenserId}`,
    JSON.stringify({ name, age, gender })
  );

  localStorage.setItem("lastDispenserId", dispenserId);

  console.log("Saved Data:", { dispenserId, name, age, gender });
  goto("/"); 
}


  function goBackToId() {
    step = 1;
    dispenserId = "";
  }
</script>

<style>
  .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: var(--color-background);
  }

  .login-card {
    background: var(--color-card);
    padding: 2rem;
    border-radius: 1rem;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    width: 350px;
    animation: fadeIn 0.4s ease;
  }

  h2 {
    text-align: center;
    color: var(--color-primary);
    margin-bottom: 1.5rem;
  }

  label {
    display: block;
    margin-bottom: 0.25rem;
    font-size: 0.9rem;
    color: var(--color-text);
  }

  input, select {
    width: 100%;
    padding: 0.6rem;
    margin-bottom: 1rem;
    border: 1px solid var(--color-text-light);
    border-radius: 0.5rem;
    font-size: 1rem;
    color: var(--color-text);
    background: #fff;
  }

  .btn-group {
    display: flex;
    gap: 0.5rem;
  }

  button {
    flex: 1;
    padding: 0.75rem;
    background-color: var(--color-primary);
    color: white;
    font-size: 1rem;
    border: none;
    border-radius: 0.5rem;
    cursor: pointer;
    transition: background 0.2s;
  }

  button:hover {
    background-color: #007f8e;
  }

  .back-btn {
    background-color: var(--color-secondary);
  }

  .back-btn:hover {
    background-color: #e69a36;
  }

  .center-btn {
    display: flex;
    justify-content: center;
  }

  .center-btn button {
    flex: unset;
    width: 100%;
  }

  @keyframes fadeIn {
    from { opacity: 0; transform: translateY(10px); }
    to { opacity: 1; transform: translateY(0); }
  }
</style>

<div class="login-container">
  <div class="login-card">
    {#if step === 1}
      <h2>Enter Dispenser ID</h2>
      <form onsubmit={e => { e.preventDefault(); handleDispenserSubmit(); }}>
        <div>
          <label for="dispenserId">Dispenser ID</label>
          <input id="dispenserId" type="text" bind:value={dispenserId} required />
        </div>
        <div class="center-btn">
          <button type="submit">Login</button>
        </div>
      </form>
    {/if}

    {#if step === 2 && isExisting}
      <h2>Welcome Back</h2>
      <p><strong>Name:</strong> {name}</p>
      <p><strong>Age:</strong> {age}</p>
      <p><strong>Gender:</strong> {gender}</p>
      <div class="btn-group">
        <button onclick={() => step = 3}>Edit</button>
        <button onclick={handleUserSubmit}>Continue</button>
      </div>
    {/if}

    {#if step === 3}
  <h2>User Details</h2>
  <form onsubmit={e => { e.preventDefault(); handleUserSubmit(); }}>
    <div>
      <label for="name">Name</label>
      <input id="name" type="text" bind:value={name} required />
    </div>

    <div>
      <label for="age">Age</label>
      <input id="age" type="number" min="0" bind:value={age} required />
    </div>

    <div>
      <label for="gender">Gender</label>
      <select id="gender" bind:value={gender} required>
        <option value="" disabled selected>Select Gender</option>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
      </select>
    </div>

    <div class="btn-group">
      {#if !isExisting}
        <button type="button" class="back-btn" onclick={goBackToId}>Back</button>
      {/if}
      <button type="submit">Save</button>
      </div>
      </form>
      {/if}
  </div>
</div>
