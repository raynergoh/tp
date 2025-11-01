# TrackerGuru Live Demo Script: Xiao Ming & Sarah

## Branch Information
**Branch:** `filter-by-tag` (Demo branch with enhanced tag filtering capability)

**Note:** This demo showcases the ability to filter by specific tags (e.g., `budget.500k`, `urgent.high`), which is implemented in this branch but not in the main branch due to feature freeze constraints.

---

## Pre-Demo Setup

### 1. Start with a clean slate
```bash
# Delete existing data file to start fresh
rm data/addressbook.json
```

### 2. Add 2-3 sample contacts (for context)
```bash
add n/John Lim p/98765432 e/john@email.com a/Blk 456 Ang Mo Kio Ave 3 #12-34 r/seller t/listing.3room t/location.amk

add n/Mary Wong p/87654321 e/mary@email.com a/Blk 789 Bedok North St 2 #08-11 r/buyer t/budget.300k t/location.bedok
```

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
"Sarah said 'two weeks.' That's 14 days to close or lose her to a competitor. Xiao Ming can't afford to treat her like just another contact."

**[Xiao Ming types]:**
```
filter t/urgent.high
```

**[Narrator]:**
"With one command, TrackerGuru surfaces all high-urgency leads. Sarah's right at the top. No scrolling. No guessing. **Focus follows priority**."

**[Screen shows filtered list with Sarah and any other urgent.high contacts]**

**[Key Rhetorical Point]:**
"While other agents are drowning in spreadsheets, Xiao Ming knows exactly who needs attention **right now**."

---

### Step 3: Finding Suitable Listings (Logos - Pattern Matching)

**[Narrator]:**
"Now, the power move. Xiao Ming needs 4-room HDBs in Tampines under $500k. Traditionally? Check three spreadsheets, cross-reference manually, pray you didn't miss anything."

**[Xiao Ming types]:**
```
filter t/rooms.4 t/location.tampines t/budget.500k
```

**[Narrator]:**
"**Three tags. Instant results.** TrackerGuru's tag system isn't decoration—it's a **search engine for your contacts**. Every contact matching Sarah's criteria appears in milliseconds. This is what happens when your CRM thinks like you do."

**[Screen shows matching contacts if any, or just Sarah]**

**[Alternative narration if no matches]:**
"No listings yet? No problem. Xiao Ming knows he needs to source for Sarah. But when he does add a listing, it'll be instantly searchable by the same tags."

**[Key Rhetorical Point]:**
"Time saved: 10 minutes. Commission protected: $500,000."

---

### Step 4: Updating Progress - Viewing (Ethos - Professionalism)

**[Narrator]:**
"Two days later, Sarah views a unit. Xiao Ming updates her status immediately—because in real estate, **speed signals competence**."

**[Xiao Ming types]:**
```
edit 1 t/status.viewing
```

**[Narrator]:**
"Notice: no forms, no menus, no clicking through three tabs. Just **type and go**. This is what professionals demand—tools that keep pace with their hustle."

**[Result shows Sarah with updated status.viewing tag]**

---

### Step 5: Updating Progress - Offer Made (Logos - Tracking)

**[Narrator]:**
"One week in, Sarah makes an offer. Xiao Ming tracks it."

**[Xiao Ming types]:**
```
edit 1 t/status.offer-made
```

**[Result shows updated status]**

**[Key Rhetorical Point]:**
"Every update is a checkpoint. Every checkpoint is confidence that nothing falls through."

---

### Step 6: Closing the Deal (Pathos - Victory)

**[Narrator]:**
"Day 13. Sarah's offer is accepted. While competitors are still looking for Sarah's phone number in their notes app, Xiao Ming closes the deal."

**[Xiao Ming types]:**
```
edit 1 t/status.closed t/urgent.none
```

**[Narrator]:**
"One contact. Two weeks. Zero leads lost. This is the difference between **hoping you remember** and **knowing you won't forget**."

**[Screen shows Sarah's contact with status.closed tag]**

**[Optional: Show list of all contacts]:**
```
list
```

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
# Optional: Clear existing data
clear

# Add sample contacts for context
add n/John Lim p/98765432 e/john@email.com a/Blk 456 Ang Mo Kio Ave 3 #12-34 r/seller t/listing.3room t/location.amk

add n/Mary Wong p/87654321 e/mary@email.com a/Blk 789 Bedok North St 2 #08-11 r/buyer t/budget.300k t/location.bedok
```

### Main Demo Commands (in order)
```bash
# 1. Add Sarah (The Hot Lead)
add n/Sarah Tan p/91234567 e/sarah.tan@email.com a/Blk 123 Tampines St 11 #05-67 r/buyer t/urgent.high t/budget.500k t/location.tampines t/rooms.4

# 2. Filter urgent leads
filter t/urgent.high

# 3. Find matching properties/contacts (demonstrate precision filtering)
filter t/rooms.4 t/location.tampines t/budget.500k

# Alternative if you want to show Sarah specifically
list

# 4. Update to viewing status
edit 1 t/status.viewing

# 5. Update to offer made
edit 1 t/status.offer-made

# 6. Close the deal
edit 1 t/status.closed t/urgent.none

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

