# TrackerGuru Demo - Filtering Scenarios Visual Guide

This document shows exactly what contacts will appear at each filtering step during your demo.

---

## Pre-Demo Contact Base (7 Total Contacts)

After running the setup commands, you'll have these contacts in the system:

| # | Name | Role | Tags | Urgency |
|---|------|------|------|---------|
| 1 | David Chen | Seller | listing.3room, location.amk, budget.400k | none |
| 2 | Mary Wong | Buyer | budget.300k, location.bedok, rooms.3, urgent.low | LOW |
| 3 | James Tan | Seller | listing.4room, location.jurong, budget.550k | none |
| 4 | Linda Lee | Buyer | budget.500k, location.tampines, rooms.4, urgent.medium | MEDIUM |
| 5 | Robert Ng | Seller | listing.5room, location.yishun, budget.650k | none |
| 6 | Michelle Koh | Buyer | budget.350k, location.pasirris, rooms.3, urgent.low | LOW |
| 7 | **Sarah Tan** | **Buyer** | **urgent.high, budget.500k, location.tampines, rooms.4** | **HIGH** |

---

## Demo Step Results

### Step 1: Add Sarah
**Command:** `add n/Sarah Tan p/91234567 e/sarah.tan@email.com a/Blk 123 Tampines St 11 #05-67 r/buyer t/urgent.high t/budget.500k t/location.tampines t/rooms.4`

**Result:** Sarah added as contact #7

**Talking Point:** "Seven fields captured in one command—name, phone, email, address, role, and four intelligent tags."

---

### Step 2: Filter Urgent Leads
**Command:** `filter t/urgent.high`

**Results Shown:**
| Name | Role | Tags | Why Shown |
|------|------|------|-----------|
| Sarah Tan | Buyer | urgent.high, budget.500k, location.tampines, rooms.4 | ✅ Has urgent.high |

**NOT Shown:**
- Linda Lee (urgent.medium) ❌
- Mary Wong (urgent.low) ❌
- Michelle Koh (urgent.low) ❌
- David Chen, James Tan, Robert Ng (no urgency tag) ❌

**Talking Point:** "Out of 7 contacts, only 1 has urgent.high. Instant prioritization. No mental overhead."

---

### Step 3: Find Matching Properties
**Command:** `filter t/rooms.4 t/location.tampines t/budget.500k`

**Results Shown:**
| Name | Role | Tags | Why Shown |
|------|------|------|-----------|
| Sarah Tan | Buyer | urgent.high, **budget.500k**, **location.tampines**, **rooms.4** | ✅ All 3 tags match |
| Linda Lee | Buyer | urgent.medium, **budget.500k**, **location.tampines**, **rooms.4** | ✅ All 3 tags match |

**NOT Shown:**
- Mary Wong (rooms.3, location.bedok, budget.300k) ❌
- James Tan (listing.4room but location.jurong, budget.550k) ❌
- Others (don't match criteria) ❌

**Talking Point:** "Two perfect matches instantly. Sarah and Linda have identical needs—potential to view properties together or compete for the same unit. This is intelligence Xiao Ming didn't manually calculate."

---

### Step 4: Update to Viewing (Progress Tracking)
**Command:** `edit 1 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled`

**Result:** 
- Status: PENDING
- Tags: urgent.high, budget.500k, location.tampines, rooms.4, viewing.scheduled (5 tags)

**Key Point:** Edit commands require ALL existing tags to be re-specified. Tags not included are removed.

**Talking Point:** "Status field for stage. Tags for details. Both preserved."

---

### Step 5: Update to Offer Made
**Command:** `edit 1 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made`

**Result:**
- Status: PENDING (unchanged)
- Tags: urgent.high, budget.500k, location.tampines, rooms.4, viewing.scheduled, offer.made (6 tags)

**Talking Point:** "Building a timeline—every milestone documented without losing context."

---

### Step 6: Close the Deal
**Command:** `edit 1 s/COMPLETED t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made t/deal.closed`

**Result:**
- Status: COMPLETED ✅
- Tags: budget.500k, location.tampines, rooms.4, viewing.scheduled, offer.made, deal.closed (6 tags)
- Note: urgent.high removed (not re-specified)

**Talking Point:** "Status: COMPLETED. Urgency cleared. Complete audit trail preserved."

---

## Bonus Filtering Scenarios

### Filter: All Buyers
**Command:** `filter r/buyer`

**Results:** Sarah Tan, Mary Wong, Linda Lee, Michelle Koh (4 contacts)

**Use Case:** "Quick view of all client leads vs. property listings"

---

### Filter: All 4-Room Seekers
**Command:** `filter t/rooms.4`

**Results:** Sarah Tan, Linda Lee (2 contacts)

**Use Case:** "When a 4-room unit becomes available, instantly see who to contact"

---

### Filter: All Tampines Contacts
**Command:** `filter t/location.tampines`

**Results:** Sarah Tan, Linda Lee (2 contacts)

**Use Case:** "Geographic targeting—new Tampines listing? These are your people."

---

### Filter: Budget Point ($500k)
**Command:** `filter t/budget.500k`

**Results:** Sarah Tan, Linda Lee (2 contacts)

**Use Case:** "Price-specific matching for targeted outreach"

---

## Why This Contact Set Works Brilliantly

### 1. **Demonstrates Precision**
- Only Sarah has `urgent.high` → Shows exact matching
- Linda matches Sarah's criteria → Shows pattern recognition
- Others have varied tags → Shows filtering actually filters

### 2. **Shows Real Complexity**
- 7 contacts = realistic workload (not trivial, not overwhelming)
- Mix of buyers/sellers = real estate context
- Varied budgets/locations = demonstrates tag diversity

### 3. **Creates "Aha!" Moments**
- **Step 2:** "Only one urgent lead!" → Focus
- **Step 3:** "Linda matches Sarah!" → Intelligence
- **Bonus demos:** "Look how versatile this is!" → Power

### 4. **Enables Storytelling**
- Xiao Ming has existing clients (not starting from scratch)
- Sarah is clearly a hot lead (urgent.high vs. others)
- Discovery of Linda creates narrative tension ("perfect match!")

---

## Common Questions & Answers

**Q: Why only 7 contacts? Isn't that too few?**
A: 7 is the cognitive sweet spot for demos:
- Large enough to show filtering value
- Small enough for audience to comprehend results
- Realistic for "one day's new contacts"
- Can reference "30 clients" in narration (beyond demo scope)

**Q: Why does Linda match Sarah exactly?**
A: This demonstrates **pattern recognition**—the killer feature. It's not just filtering inputs; it's discovering connections the user didn't know existed.

**Q: What if someone asks about sellers with 4-room listings?**
A: James Tan has `listing.4room` but in Jurong at $550k, so he won't appear in the Tampines/$500k filter. This shows precision—**filtering works correctly**.

**Q: Should I show all bonus filters?**
A: Pick 1-2 based on time. Recommended:
1. `filter r/buyer` (quick, impressive count)
2. `filter t/location.tampines` (shows geographic power)

---

## Demo Success Metrics

After your demo, evaluators should remember:

✅ **The Problem:** Agents juggle too many contacts, lose track of priorities
✅ **The Solution:** One-command filtering with intelligent tags
✅ **The Proof:** Sarah filtered from 7 contacts in <1 second
✅ **The Surprise:** Linda discovered as a perfect match
✅ **The Tagline:** "Track smarter. Close faster. Win more."

---

## Quick Reference: Expected Counts

| Filter Command | Expected Count | Names |
|----------------|----------------|-------|
| `list` | 7 contacts | All |
| `filter t/urgent.high` | 1 contact | Sarah only |
| `filter t/rooms.4 t/location.tampines t/budget.500k` | 2 contacts | Sarah, Linda |
| `filter r/buyer` | 4 contacts | Sarah, Mary, Linda, Michelle |
| `filter t/rooms.4` | 2 contacts | Sarah, Linda |
| `filter t/location.tampines` | 2 contacts | Sarah, Linda |
| `filter t/budget.500k` | 2 contacts | Sarah, Linda |

**Use this table to verify your demo is working correctly before presenting!**

---

## Final Pre-Demo Checklist

- [ ] Run all pre-demo commands (6 contacts + clear)
- [ ] Verify `list` shows exactly 7 contacts after adding Sarah
- [ ] Test `filter t/urgent.high` → Should show ONLY Sarah
- [ ] Test `filter t/rooms.4 t/location.tampines t/budget.500k` → Should show Sarah + Linda
- [ ] Practice narration emphasizing "Linda matches Sarah!"
- [ ] Have this document open for quick reference during demo

**You're ready to showcase TrackerGuru's intelligence!** 🎯

