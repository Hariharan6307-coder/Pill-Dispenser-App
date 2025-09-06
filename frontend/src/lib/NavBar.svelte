<script>
  import { page } from "$app/stores";

  const navItems = [
    {
      name: "Calendar",
      path: "/calendar",
      icon: "/images/icons/calendar-icon.png",
      iconSelected: "/images/icons/calendar-selected-icon.png"
    },
    {
      name: "Pill",
      path: "/pill-slots",
      icon: "/images/icons/pill-icon.png",
      iconSelected: "/images/icons/pill-selected-icon.png"
    },
    {
      name: "Home",
      path: "/",
      icon: "/images/icons/home-icon.png",
      iconSelected: "/images/icons/home-selected-icon.png"
    },
    {
      name: "Profile",
      path: "/profile",
      icon: "/images/icons/profile-icon.png",
      iconSelected: "/images/icons/profile-selected-icon.png"
    }
  ];
</script>

<nav class="navbar">
  {#each navItems as item}
    <a 
      href={item.path} 
      class="nav-item {($page.url.pathname === item.path) ? 'active' : ''}"
    >
      <img 
        src={($page.url.pathname === item.path) ? item.iconSelected : item.icon} 
        alt={item.name} 
      />
      <span>{($page.url.pathname === item.path) ? item.name : ""}</span>
    </a>
  {/each}
</nav>

<style>
  .navbar {
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    height: 70px; /* adjust as needed */
    display: flex;
    background-color: var(--color-primary);
    z-index: 1000;
  }

  .nav-item {
    flex: 1; /* 🔥 each item takes equal space */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-decoration: none;
    color: var(--color-card);
    font-size: 0.9rem;
    padding: 6px 0;
    border-radius: 0; /* reset if you don't want curved edges per item */
    transition: background-color 0.2s ease;
  }

  .nav-item img {
    width: 28px;
    height: 28px;
    margin-bottom: 0.2rem;
  }

  .nav-item.active {
    background-color: var(--color-card);
    color: var(--color-primary);
  }

  .nav-item.active span {
    font-weight: bold;
    color: var(--color-primary);
  }
</style>
