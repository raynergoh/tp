# Important: Edit Command Behavior & Status Field Usage

## Critical Understanding for Demo

### 🚨 Tag Preservation Behavior

**The `edit` command is NOT cumulative for tags!**

When you use `edit`, you must **re-specify ALL tags** you want to keep. Tags not included in the edit command will be **removed**.

---

## How Edit Works

### ❌ What DOESN'T Work
```bash
# Initial state: Sarah has t/urgent.high t/budget.500k t/location.tampines t/rooms.4

# Trying to add just one tag:
edit 1 t/viewing.scheduled

# Result: Sarah now has ONLY t/viewing.scheduled
# ❌ Lost: urgent.high, budget.500k, location.tampines, rooms.4
```

### ✅ What DOES Work
```bash
# Initial state: Sarah has t/urgent.high t/budget.500k t/location.tampines t/rooms.4

# Correct way to add a tag:
edit 1 t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled

# Result: Sarah now has all 5 tags
# ✅ Kept: urgent.high, budget.500k, location.tampines, rooms.4
# ✅ Added: viewing.scheduled
```

---

## Status Field Usage

### What is the Status Field?

**Status** is a separate enum field (not a tag) that tracks deal stage:
- `PENDING` - Deal in progress
- `COMPLETED` - Deal closed

### How to Use Status

```bash
# Set status to PENDING
edit 1 s/PENDING t/[all tags here]

# Set status to COMPLETED
edit 1 s/COMPLETED t/[all tags here]
```

**Note:** Status field is independent from tags. You can change status without affecting tags, but you must still re-specify all tags to preserve them.

---

## Demo Command Breakdown

### Initial Add (Step 1)
```bash
add n/Sarah Tan p/91234567 e/sarah.tan@email.com a/Blk 123 Tampines St 11 #05-67 r/buyer t/urgent.high t/budget.500k t/location.tampines t/rooms.4
```

**Sarah's state after add:**
- Status: (none/default)
- Tags: `urgent.high`, `budget.500k`, `location.tampines`, `rooms.4`

---

### Viewing Scheduled (Step 4)
```bash
edit 1 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled
```

**Sarah's state after edit:**
- Status: `PENDING`
- Tags: `urgent.high`, `budget.500k`, `location.tampines`, `rooms.4`, `viewing.scheduled`

**What happened:**
- ✅ Status set to PENDING
- ✅ All 4 original tags preserved (re-specified)
- ✅ New tag `viewing.scheduled` added

---

### Offer Made (Step 5)
```bash
edit 1 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made
```

**Sarah's state after edit:**
- Status: `PENDING`
- Tags: `urgent.high`, `budget.500k`, `location.tampines`, `rooms.4`, `viewing.scheduled`, `offer.made`

**What happened:**
- ✅ Status kept as PENDING
- ✅ All 5 previous tags preserved (re-specified)
- ✅ New tag `offer.made` added

---

### Deal Closed (Step 6)
```bash
edit 1 s/COMPLETED t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made t/deal.closed
```

**Sarah's state after edit:**
- Status: `COMPLETED`
- Tags: `budget.500k`, `location.tampines`, `rooms.4`, `viewing.scheduled`, `offer.made`, `deal.closed`

**What happened:**
- ✅ Status changed to COMPLETED
- ⚠️ `urgent.high` tag removed (not re-specified - intentional!)
- ✅ Other tags preserved
- ✅ New tag `deal.closed` added

**Why remove urgent.high?** Deal is complete, so urgency no longer applies. This demonstrates selective tag management.

---

## Tag Management Strategy

### Tags to Preserve Throughout
These describe Sarah's needs and should stay:
- `budget.500k`
- `location.tampines`
- `rooms.4`

### Tags to Remove Eventually
These are temporary state indicators:
- `urgent.high` - Remove when deal closes

### Tags to Add Progressively
These build a timeline:
- `viewing.scheduled` - Added when viewing arranged
- `offer.made` - Added when offer submitted
- `deal.closed` - Added when deal completes

---

## Common Mistakes to Avoid

### ❌ Mistake 1: Forgetting to Re-specify Tags
```bash
edit 1 s/PENDING t/viewing.scheduled
# This removes all original tags!
```

### ❌ Mistake 2: Typos in Tag Names
```bash
edit 1 t/urgent.high t/budget.500k t/location.tampinese t/rooms.4
#                                               ^ typo!
# Creates a new tag "location.tampinese" instead of "location.tampines"
```

### ❌ Mistake 3: Forgetting Status Field
```bash
edit 1 t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled
# Without s/PENDING, status remains unchanged
```

---

## Filtering with Status

### Filter by Status Field
```bash
# Show all pending deals
filter s/PENDING

# Show all completed deals
filter s/COMPLETED
```

### Combined Status + Tag Filtering
```bash
# Show all pending 4-room deals
filter s/PENDING t/rooms.4

# Show all completed Tampines deals
filter s/COMPLETED t/location.tampines
```

---

## Quick Copy-Paste Commands (Verified Correct)

### Step 4: Add Viewing Tag
```bash
edit 1 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled
```

### Step 5: Add Offer Tag
```bash
edit 1 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made
```

### Step 6: Close Deal
```bash
edit 1 s/COMPLETED t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled t/offer.made t/deal.closed
```

---

## Verification Checklist

Before each edit, verify:
- [ ] All existing tags are re-specified
- [ ] New tag is added at the end
- [ ] Status field is set correctly (s/PENDING or s/COMPLETED)
- [ ] Tag names match exactly (case-sensitive!)
- [ ] No spaces in tag values (use dots: `location.tampines` not `location.Tampines`)

After each edit, check result:
- [ ] All expected tags are present
- [ ] No unexpected tags were removed
- [ ] Status field shows correct value

---

## Why This Matters for Demo

### Narrative Impact
The progressive tag addition tells a story:
1. **Initial:** urgent.high, budget.500k, location.tampines, rooms.4 → "Hot lead identified"
2. **Viewing:** + viewing.scheduled → "Engagement happening"
3. **Offer:** + offer.made → "Deal progressing"
4. **Closed:** - urgent.high, + deal.closed, status COMPLETED → "Success!"

### Professional Credibility
Showing proper tag management demonstrates:
- **Attention to detail** (preserving important data)
- **Progressive documentation** (building timeline)
- **Status tracking** (using the right tool for the job)

---

## Emergency Recovery

### If You Accidentally Remove Tags

**Scenario:** You ran `edit 1 t/viewing.scheduled` and lost all Sarah's tags.

**Recovery:**
```bash
# Re-add all tags manually
edit 1 s/PENDING t/urgent.high t/budget.500k t/location.tampines t/rooms.4 t/viewing.scheduled
```

**Prevention:** Always have the full command pre-written in your notes!

---

## Key Takeaway

**Remember:** Edit is like rewriting the entire tag list. Include everything you want to keep!

✅ **Status field** = Stage of deal (PENDING/COMPLETED)  
✅ **Tags** = Details, criteria, timeline markers  
✅ **Edit** = Re-specify ALL tags you want to preserve

**Practice these commands 3-5 times before the demo to build muscle memory!**

