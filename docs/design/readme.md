1)
As darkness and light now shift across the land, the world follows a new rhythm the Cycle of Time.
At the core of this system is the World Cycle Manager, which advances the time of day every few turns. Creatures and structures that respond to time, allowing them to change behaviour as morning turns to night.
When darkness falls, beasts become more dangerous and hostile, gaining power under the cover of night.
Wanderers may come across sacred Shrines marked by the symbol s. These ancient structures offer players the chance to pray, but only during the day and right time. Players are healed at morning or empowered at night, while the shrine remains silent at other times.

Brief Implementation Design
1) WorldCycleManager that controls time
2) NightAggressiveBehaviour that controls behaviour that makes enemy more aggressive and stronger at night
3) RestAction, a time condition action allowing the Player (Farmer) to pray, where resting in the morning result in healing, and at night result in damage buff being added and higher chance of hitting and increase in health as well
4) CampFire, a Ground tite that offers the RestAction when player is nearby (8x8)
5) TimeSensitiveEntity, an interface that allow reaction to time changes
      
Design principals considered:
a) SRP: Each class is focused on 1 task, for example WorldCycleManager manages time only, and NightAggressiveBehaviour only modifies enemy aggressions
b) OCP: The behvaiour can be added by implementing the interface without modifying the existing logic
c) DRY: instead f checking time manually in each class, we notify all classes centrally, this prevent duplicated logic and conditions across the system.



2)
Farmer's assistant 

Can have multiple assistants each with their own jobs (?) or maybe one assistant with an action to swap current job

eg: planting, holding runes to accumulate interest, pick up items

Has its own inventory that holds items picked up from its job,
and the farmer can also pass items into it 