<script>
  export let time = "7:30 PM";
  export let day = "Today";
  export let image = "images/icons/pills.png"; 
</script>

<div class="pill-reminder">
  <div class="pill-text">
    <p class="pill-next">Your next pill to be taken at</p>
    <p class="pill-time">{time}</p>
    <p class="pill-day">{day}</p>
  </div>
  <img src={image} alt="" class="pill-image"/>
</div>

<style>
  .pill-reminder {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background: linear-gradient(90deg, #4BC0C8, #00C6FF);
    padding: 24px;
    border-radius: 20px;
    max-width: 500px;
    margin: 20px auto;
    color: white;
    font-family: sans-serif;
  }

  .pill-text {
    display: flex;
    flex-direction: column;
  }

  .pill-next {
    font-size: 1rem;
    margin: 0;
    font-weight: 600;
  }

  .pill-time {
    font-size: 2rem;
    font-weight: bold;
    margin: 4px 0;
  }

  .pill-day {
    font-size: 1rem;
    margin: 0;
  }

  .pill-image {
    width: 80px;
    height: 80px;
    object-fit: contain;
  }
</style>
