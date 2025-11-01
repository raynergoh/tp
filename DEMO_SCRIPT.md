# TrackerGuru Live Demo Script: Xiao Ming & Sarah

## Branch Information
**Branch:** `filter-by-tag` (Demo branch with enhanced tag filtering capability)

**Note:** This demo showcases the ability to filter by specific tags (e.g., `budget.500k`, `urgent.high`), which is implemented in this branch but not in the main branch due to feature freeze constraints.

---

## ⚠️ CRITICAL: Edit Command Behavior

**READ THIS BEFORE DEMO!**

### Edit Commands Are NOT Cumulative for Tags

When using `edit`, you must **re-specify ALL tags** you want to keep. Tags not included will be **removed**.

**Wrong:**
```bash
edit 1 t/viewing.scheduled  # This REMOVES all other tags!
```

**Correct:**
```bash
edit 1 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled
```

### Status Field Usage
- **Status** is a separate enum field: `s/PENDING` or `s/COMPLETED`
- **Not a tag!** Don't use `t/status.anything`

### Tag Evolution Strategy
- **Keep throughout:** budget.500k, location.tampines, rooms.4 (describes needs)
- **Remove when done:** urgent.high (temporary priority)
- **Add progressively:** viewing.scheduled, offer.made, deal.closed (timeline)

**Practice these commands 3-5 times before presenting!**

---

## Pre-Demo Setup

### 1. Start with a clean slate
```bash
# Delete existing data file to start fresh
clear
```

### 2. Create Tag Groups (Required for tag organization)

TrackerGuru uses tag groups to organize tags. Create all required tag groups first:

```bash
# Create tag groups for the demo
tg listing
tg location
tg budget
tg rooms
tg urgent
tg viewing
tg offer
tg deal
```

**Why these tag groups:**
- `listing` - For property listings (listing.3room, listing.4room, etc.)
- `location` - For geographic areas (location.amk, location.tampines, etc.)
- `budget` - For price points (budget.300k, budget.500k, etc.)
- `rooms` - For room specifications (rooms.3, rooms.4, etc.)
- `urgent` - For priority levels (urgent.low, urgent.medium, urgent.high)
- `viewing` - For viewing milestones (viewing.scheduled)
- `offer` - For offer tracking (offer.made)
- `deal` - For deal closure (deal.closed)

### 3. Add realistic sample contacts (for context - shows Xiao Ming's existing workload)

These contacts are strategically designed to showcase filtering capabilities:
- **Varied urgency levels** (high, medium, low) - to show priority filtering
- **Multiple locations** - to show geographic filtering
- **Different budgets** - to show price range filtering
- **Different room types** - to show specification matching
- **Mix of buyers and sellers** - to show role filtering

```bash
# Contact 1: Seller with 3-room in AMK
add n/David Chen p/98765432 e/david@email.com a/Blk 456 Ang Mo Kio Ave 3 #12-34 r/seller t/listing.3room t/location.amk t/budget.400k

# Contact 2: Low-urgency buyer looking for 3-room in Bedok
add n/Mary Wong p/87654321 e/mary@email.com a/Blk 789 Bedok North St 2 #08-11 r/buyer t/budget.300k t/location.bedok t/rooms.3 t/urgent.low

# Contact 3: Seller with 4-room in Jurong (higher budget)
add n/James Tan p/91112233 e/james@email.com a/Blk 234 Jurong West St 52 #06-18 r/seller t/listing.4room t/location.jurong t/budget.550k

# Contact 4: Seller with PERFECT PROPERTY for Sarah!
add n/Linda Lee p/82223344 e/linda@email.com a/Blk 88 Tampines Ave 10 #10-25 r/seller t/listing.4room t/location.tampines t/budget.500k

# Contact 5: Seller with 5-room in Yishun (premium)
add n/Robert Ng p/93334455 e/robert@email.com a/Blk 102 Yishun Ring Rd #03-07 r/seller t/listing.5room t/location.yishun t/budget.650k

# Contact 6: Low-urgency buyer for 3-room in Pasir Ris
add n/Michelle Koh p/84445566 e/michelle@email.com a/Blk 567 Pasir Ris St 51 #12-88 r/buyer t/budget.350k t/location.pasirris t/rooms.3 t/urgent.low
```

**Why these contacts matter:**
- Linda Lee is a **SELLER with the PERFECT property** for Sarah (4-room, Tampines, $500k)
- This creates an immediate viewing opportunity and potential deal
- Varied urgency levels show that Sarah's `urgent.high` stands out
- Multiple locations demonstrate precise geographic filtering
- Mix of buyers/sellers shows real workload complexity

---

## Demo Script

### Introduction (Pathos - Hook)

**[Narrator]:**
"Meet Xiao Ming, a property agent juggling 30 clients a day. Yesterday, he missed a follow-up with a hot lead—and lost a $500,000 deal. Sound familiar?"

**[Pause for 2 seconds]**

"Today, everything changes. Meet Sarah—a buyer who needs a 4-room HDB in Tampines within 2 weeks. Let's watch how TrackerGuru ensures Sarah never slips through the cracks."

---

### Step 1: Adding Sarah as a Contact (Logos - Efficiency)

**[Narrator]:**
"It's 9 AM. Sarah just called. While she's still on the phone, Xiao Ming captures every detail in seconds—no switching between apps, no sticky notes."

**[Xiao Ming types]:**
```
add n/Sarah Tan p/91234567 e/sarah.tan@email.com a/Blk 123 Tampines St 11 #05-67 r/buyer t/urgent.high t/budget.500k t/location.tampines t/rooms.4
```

**[Narrator]:**
"One command. Seven fields. Done. Notice how TrackerGuru uses **tag groups**—`urgent.high`, `budget.500k`, `location.tampines`, `rooms.4`. This isn't just data entry. This is **intelligent categorization**. Every tag becomes a filter, a priority signal, a search key."

**[Result shows Sarah added to the list]**

**[Key Rhetorical Point]:**
"In the time it takes to open Excel, Xiao Ming has already captured everything that matters."

---

### Step 2: Prioritizing Sarah (Pathos - Fear of Loss)

**[Narrator]:**
"Sarah said 'two weeks.' That's 14 days to close or lose her to a competitor. Xiao Ming can't afford to treat her like just another contact. With 7 clients in his system, he needs to instantly identify his highest priorities."

**[Xiao Ming types]:**
```
filter t/urgent.high
```

**[Narrator]:**
"With one command, TrackerGuru surfaces all high-urgency leads. Sarah's right at the top—the ONLY urgent.high contact. Not Mary or Michelle with their low urgency. Just Sarah."

**[Screen shows filtered list with ONLY Sarah]**

**[Key Rhetorical Point]:**
"While other agents are drowning in spreadsheets, scanning through dozens of names, Xiao Ming knows exactly who needs attention **right now**. One second. One command. Zero confusion."

**[Optional: Show contrast]:**
```
list
```
**[Narrator briefly]:** "Seven total contacts. But filtering? Just one high-priority lead. That's precision."

---

### Step 3: Finding Suitable Listings (Logos - Pattern Matching)

**[Narrator]:**
"Now, the power move. Sarah needs a 4-room HDB in Tampines under $500k. Xiao Ming has 7 contacts in his system. Traditionally? Scroll through each one. Read each tag. Cross-reference manually. Hope you didn't miss anyone."

**[Pause]**

"Watch this."

**[Xiao Ming types]:**
```
filter t/listing.4room t/location.tampines t/budget.500k
```

**[Narrator]:**
"**Three tags. Instant results.** One match appears: Linda Lee!"

**[Screen shows Linda only]**

**[Narrator - with excitement]:**
"Linda is a SELLER with a 4-room HDB in Tampines listed at exactly $500k. This isn't just a match—this is **the perfect property** for Sarah. Notice: James has a 4-room listing but not in Tampines. Sarah needs a 4-room in Tampines but she's a buyer, not a seller. Only Linda has **all three criteria**—that's precision filtering. But here's the magic: Xiao Ming didn't remember Linda's listing had these exact specifications. He would have had to scroll through all 7 contacts, reading each tag manually. But TrackerGuru found her in one second."

**[Key Insight Moment]:**
"This isn't just filtering. This is **instant opportunity recognition**. In the time it takes other agents to open their spreadsheet, Xiao Ming is already texting Sarah: 'I have the perfect property. Can you view it today?'"

**[Pause for effect]**

"That's the difference between **searching** and **finding**. Between **working hard** and **working smart**."

**[Key Rhetorical Point]:**
"Time saved: 10 minutes. Viewing scheduled: Today. Commission potential: $500,000."

---

### Step 3b: Return to Full View & Identify Contacts

**[Narrator]:**
"Linda's property is perfect. Now Xiao Ming needs to update both contacts as the deal progresses."

**[Xiao Ming types]:**
```
list
```

**[Narrator]:**
"Back to the full view. Sarah is contact #7. Linda is contact #4. Both need to be tracked as this deal unfolds."

**[Screen shows all 7 contacts with Sarah at position 7 and Linda at position 4]**

---

### Step 4: Updating Progress - Viewing Scheduled (Ethos - Professionalism)

**[Narrator]:**
"Two days later, Sarah views Linda's property. Xiao Ming updates BOTH contacts—because in real estate, **every touchpoint matters**. Sarah first."

**[Xiao Ming types]:**
```
edit 7 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled
```

**[Narrator]:**
"Notice the number: 7. That's Sarah's position in the list. Status set to PENDING, viewing tag added, all original tags preserved."

**[Result shows Sarah updated]**

**[Narrator]:**
"Now Linda, the seller."

**[Xiao Ming types]:**
```
edit 4 s/PENDING t/listing.4room t/location.tampines t/budget.500k t/viewing.scheduled
```

**[Result shows Linda updated with viewing tag]**

**[Narrator]:**
"No forms. No menus. Just **type and go**. Both contacts updated in seconds. This is what professionals demand—tools that keep pace with their hustle."

**[Result shows Sarah with PENDING status and added viewing.scheduled tag, all original tags preserved]**

---

### Step 5: Updating Progress - Offer Made (Logos - Tracking)

**[Narrator]:**
"One week in, Sarah makes an offer on Linda's property. Xiao Ming updates both contacts to reflect this milestone."

**[Xiao Ming types]:**
```
edit 7 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made
```

**[Narrator]:**
"Sarah's offer is documented."

**[Result shows offer.made tag added to Sarah's existing tags]**

**[Xiao Ming types]:**
```
edit 4 s/PENDING t/listing.4room t/location.tampines t/budget.500k t/viewing.scheduled t/offer.received
```

**[Narrator]:**
"And Linda's property now shows an offer has been received."

**[Result shows offer.received tag added to Linda's tags]**

**[Key Rhetorical Point]:**
"Every update is a checkpoint. Every checkpoint is confidence that nothing falls through. The tags build a complete timeline—both sides of the transaction, everything documented."

---

### Step 6: Closing the Deal (Pathos - Victory)

**[Narrator]:**
"Day 13. The offer is accepted. While competitors are still looking for phone numbers in their notes app, Xiao Ming closes the deal for both parties."

**[Xiao Ming types]:**
```
edit 7 s/COMPLETED t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made t/deal.closed
```

**[Narrator]:**
"Sarah's status: COMPLETED. Urgency cleared since the deal is done."

**[Result shows Sarah with COMPLETED status]**

**[Xiao Ming types]:**
```
edit 4 s/COMPLETED t/listing.4room t/location.tampines t/budget.500k t/viewing.scheduled t/offer.received t/deal.closed
```

**[Narrator]:**
"And Linda's property: SOLD. Both sides of the transaction documented."

**[Result shows Linda with COMPLETED status]**

**[Narrator]:**
"Two contacts. Two weeks. Zero leads lost. One successful transaction. This is the difference between **hoping you remember** and **knowing you won't forget**."

**[Optional: Show list of all contacts]:**
```
list
```

---

### Bonus Demonstrations (If Time Permits - Shows Versatility)

These quick demonstrations showcase the filtering system's flexibility and power across different use cases.

#### Show All Buyers
**[Xiao Ming types]:**
```
filter r/buyer
```

**[Narrator]:**
"Need to see all buyers in one view? One command. Three results: Sarah, Mary, Michelle. Every buyer lead, instantly accessible."

**[Screen shows 3 buyers]**

---

#### Show All 4-Room Listings
**[Xiao Ming types]:**
```
filter t/listing.4room
```

**[Narrator]:**
"Looking for all 4-room properties? James has one in Jurong, Linda has one in Tampines. Same instant results."

**[Screen shows James and Linda]**

---

#### Geographic Filtering
**[Xiao Ming types]:**
```
filter t/location.tampines
```

**[Narrator]:**
"Focus on a specific area? Every Tampines contact appears. Perfect for when a new listing comes up—instant match to interested buyers."

**[Screen shows Tampines contacts]**

---

#### Budget Filtering
**[Xiao Ming types]:**
```
filter t/budget.500k
```

**[Narrator]:**
"Price point filtering. Every contact at this exact budget level. When a $500k unit becomes available, Xiao Ming knows exactly who to call."

**[Screen shows $500k budget contacts]**

---

#### Return to Full View
**[Xiao Ming types]:**
```
list
```

**[Narrator]:**
"And back to the complete picture. Any filter, any time, and always one command away from your full contact base."

**[Key Insight]:**
"This isn't just organization. It's **on-demand intelligence**. Every tag is a query. Every query takes one second. Every second saved is a competitive advantage."

---

### Closing (Ethos + Pathos - Call to Action)

**[Narrator]:**
"TrackerGuru didn't just manage a contact. It **protected a commission**. It turned chaos into clarity. It transformed Xiao Ming from overwhelmed to in control."

**[Pause for 2 seconds]**

"Because in property, the agent who responds fastest wins. And with TrackerGuru, **you're always fastest**."

**[Final screen showing organized contact list with Sarah marked as closed]**

**[Final Rhetorical Flourish]:**
"TrackerGuru. Track smarter. Close faster. Win more."

---

## Command Summary (Sequential Order)

### Pre-Demo Setup Commands
```bash
# Clear existing data
clear

# Create all required tag groups
tg listing
tg location
tg budget
tg rooms
tg urgent
tg viewing
tg offer
tg deal

# Add diverse contact base (6 contacts showing varied criteria)
add n/David Chen p/98765432 e/david@email.com a/Blk 456 Ang Mo Kio Ave 3 #12-34 r/seller t/listing.3room t/location.amk t/budget.400k

add n/Mary Wong p/87654321 e/mary@email.com a/Blk 789 Bedok North St 2 #08-11 r/buyer t/budget.300k t/location.bedok t/rooms.3 t/urgent.low

add n/James Tan p/91112233 e/james@email.com a/Blk 234 Jurong West St 52 #06-18 r/seller t/listing.4room t/location.jurong t/budget.550k

add n/Linda Lee p/82223344 e/linda@email.com a/Blk 88 Tampines Ave 10 #10-25 r/seller t/listing.4room t/location.tampines t/budget.500k

add n/Robert Ng p/93334455 e/robert@email.com a/Blk 102 Yishun Ring Rd #03-07 r/seller t/listing.5room t/location.yishun t/budget.650k

add n/Michelle Koh p/84445566 e/michelle@email.com a/Blk 567 Pasir Ris St 51 #12-88 r/buyer t/budget.350k t/location.pasirris t/rooms.3 t/urgent.low
```

### Main Demo Commands (in order)
```bash
# 1. Add Sarah (The Hot Lead)
add n/Sarah Tan p/91234567 e/sarah.tan@email.com a/Blk 123 Tampines St 11 #05-67 r/buyer t/urgent.high t/budget.500k t/location.tampines t/rooms.4

# 2. Filter urgent leads
filter t/urgent.high

# 3. Find matching property for Sarah
filter t/listing.4room t/location.tampines t/budget.500k

# 3b. Return to full list to see contact positions
list

# 4a. Update Sarah (contact #7) - Viewing scheduled
edit 7 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled

# 4b. Update Linda (contact #4) - Viewing scheduled
edit 4 s/PENDING t/listing.4room t/location.tampines t/budget.500k t/viewing.scheduled

# 5a. Update Sarah (contact #7) - Offer made
edit 7 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made

# 5b. Update Linda (contact #4) - Offer received
edit 4 s/PENDING t/listing.4room t/location.tampines t/budget.500k t/viewing.scheduled t/offer.received

# 6a. Close deal for Sarah (contact #7)
edit 7 s/COMPLETED t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made t/deal.closed

# 6b. Close deal for Linda (contact #4)
edit 4 s/COMPLETED t/listing.4room t/location.tampines t/budget.500k t/viewing.scheduled t/offer.received t/deal.closed

# 7. Show final result
list
```

---

## Rhetorical Devices Used

### Pathos (Emotional Appeal)
- **Fear of loss**: "missed a follow-up...lost a $500,000 deal"
- **Victory**: "One contact. Two weeks. Zero leads lost."
- **Anxiety relief**: "ensures Sarah never slips through the cracks"

### Logos (Logical Appeal)
- **Hard numbers**: "One command. Seven fields." | "Three tags. Instant results."
- **Time savings**: "In seconds—no switching between apps"
- **Efficiency metrics**: "Time saved: 10 minutes. Commission protected: $500,000."

### Ethos (Credibility)
- **Professionalism**: "speed signals competence"
- **Tool mastery**: "tools that keep pace with their hustle"
- **Expertise**: "This is what professionals demand"

### Stylistic Techniques
- **Repetition**: "No scrolling. No guessing." | "One contact. Two weeks. Zero leads lost."
- **Contrast**: "While competitors are still looking for..." vs "Xiao Ming closes the deal"
- **Rule of Three**: "Track smarter. Close faster. Win more."
- **Parallel Structure**: "It protected a commission. It turned chaos into clarity. It transformed..."
- **Rhetorical Questions**: "Sound familiar?"

---

## Tips for Delivery

### Pacing
- **Speak slowly** during commands (let audience read)
- **Pause** after key rhetorical points (2-3 seconds)
- **Speed up** during transitions to maintain energy

### Emphasis
- **Bold words** in script = louder/slower delivery
- Use vocal inflection on numbers ("$500,000")
- Emphasize contrasts ("hoping you remember" vs "knowing you won't forget")

### Screen Management
- Keep window maximized and font large
- Use a **clean data file** (only demo contacts)
- Have commands ready in a text file for quick copy-paste if needed

### Backup Plan
- If a command fails, have the correct syntax visible
- Practice the demo flow 3-5 times to build muscle memory
- Keep a printed copy of commands as reference

---

## Technical Notes

### Why This Branch?
The `filter-by-tag` branch implements precise tag filtering (e.g., `filter t/budget.500k`), which is not available in the main branch due to feature freeze. This capability is essential for the demo narrative of "instant pattern matching."

### Reverting After Demo
After the presentation, you can switch back to the main branch:
```bash
git checkout main
```

### Line Count of Changes
Approximately **35 lines** of functional code changed across 4 files:
1. `MatchesRoleStatusTagGroupPredicate.java` (~15 lines)
2. `FilterCommandParser.java` (~10 lines)
3. `FilterCommand.java` (~5 lines)
4. `CliSyntax.java` (~0 lines - PREFIX_TAG already existed)

All test files updated to maintain compatibility.

---

## Success Metrics

After this demo, evaluators should understand:
1. ✅ **The Problem**: Agents lose deals due to poor contact management
2. ✅ **The Solution**: TrackerGuru's intelligent tagging and filtering
3. ✅ **The Value**: Time saved = commissions protected
4. ✅ **The Differentiator**: Speed and precision vs traditional CRMs

**Target Emotional Response**: "I need this tool. It would solve my exact problem."

