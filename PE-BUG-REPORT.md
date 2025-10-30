# TrackerGuru - Practical Exam Bug Report
## Tester: Self-Testing (Pre-PE Preparation)
## Date: October 30, 2025

This document lists potential bugs identified during self-testing of TrackerGuru v1.6, organized by severity and type.

---

## Bug #1: Phone Number Validation Too Permissive
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Medium`

### Description
The phone number validation accepts any sequence of 3 or more digits, including unrealistic phone numbers like "111", "000", or "99999999999999999999" (20+ digits). For a property agent application targeting real-world use, this is problematic as it allows invalid phone numbers that cannot be used to contact clients.

### Steps to Reproduce
1. Launch TrackerGuru
2. Execute: `add n/Test Person p/111 e/test@example.com a/123 Street`
3. Execute: `add n/Test Person2 p/00000000000000000000 e/test2@example.com a/123 Street`

### Expected
Phone numbers should have reasonable validation (e.g., 8-15 digits for Singapore context, no leading zeros for mobile numbers, etc.)

### Actual
Both commands succeed and accept invalid phone numbers.

### Justification
This is a feature flaw because the validation doesn't match real-world requirements for property agents who need valid contact numbers. The feature is "incomplete" as it doesn't properly validate what constitutes a valid phone number for the target user's context.

---

## Bug #2: Email Validation Accepts Single-Character Local Parts
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Low`

### Description
The email validation accepts emails with single-character local parts (e.g., "a@example.com"). While technically valid per RFC standards, such emails are extremely rare and could indicate user input errors in a professional property management context.

### Steps to Reproduce
1. Execute: `add n/John Doe p/98765432 e/a@b.co a/123 Street`

### Expected
Either accept this as valid (with documentation noting it's allowed) or require a minimum local-part length for practical use.

### Actual
Accepted without warning.

### Justification
Feature flaw - the validation may be too permissive for the target user context where professional email addresses are expected.

---

## Bug #3: Duplicate Phone/Email Not Checked Case-Insensitively for Email
**Type:** `type.FunctionalityBug`  
**Severity:** `severity.Medium`

### Description
While the UG states that each person must have a unique phone and email, the duplicate email check may be case-sensitive. Email addresses are case-insensitive by RFC standards (test@example.com = TEST@example.com), so the system should prevent both.

### Steps to Reproduce
1. Execute: `add n/John Doe p/98765432 e/john@example.com a/123 Street`
2. Execute: `add n/Jane Doe p/98765433 e/JOHN@example.com a/456 Street`

### Expected
Second command should fail with duplicate email error.

### Actual
(Need to test - if it allows it, this is a bug)

---

## Bug #4: Role Validation Allows Trailing Spaces Despite Trimming
**Type:** `type.FunctionalityBug`  
**Severity:** `severity.Low`

### Description
The Role validation regex allows roles to end with spaces, but the parser trims input. This could lead to confusion where "Buyer " (with trailing space) and "Buyer" are treated as different in the regex but same after trimming.

### Code Location
`Role.java` line 20: `VALIDATION_REGEX = "^[A-Za-z0-9][A-Za-z0-9 _-]*[A-Za-z0-9_-]$|^[A-Za-z0-9]$"`

### Steps to Reproduce
Review the code - the second alternative `^[A-Za-z0-9]$` suggests single-character roles don't need the ending constraint, but multi-character roles can't end with space. However, trimming happens before validation.

### Expected
Validation regex should match the trimmed input behavior.

### Actual
Regex may be unnecessarily complex given that input is trimmed.

---

## Bug #5: Name Field Accepts Very Long Names Without Warning
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.VeryLow`

### Description
The name field accepts extremely long names (e.g., 500+ characters) which could cause UI display issues or performance problems.

### Steps to Reproduce
1. Execute: `add n/AAAAAAAAAA[...repeat 500 times...]AAAA p/98765432 e/test@example.com a/123 Street`

### Expected
Either accept with proper UI handling, or impose a reasonable maximum length (e.g., 100-200 characters).

### Actual
(Need to test - likely accepts without limit)

---

## Bug #6: Address Field Accepts Any Non-Empty String Including Single Character
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Low`

### Description
The address validation only checks that the first character is not whitespace (`[^\\s].*`), meaning "a" is a valid address. For property agents managing real addresses, this is too permissive.

### Steps to Reproduce
1. Execute: `add n/John Doe p/98765432 e/john@example.com a/x`

### Expected
Addresses should have a minimum reasonable length (e.g., 5+ characters).

### Actual
Accepts single-character addresses.

---

## Bug #7: Status Case-Insensitive But Not Documented Clearly
**Type:** `type.DocumentationBug`  
**Severity:** `severity.VeryLow`

### Description
The UG mentions status is case-insensitive, but examples only show capitalized forms. Additional examples with mixed case would clarify this behavior.

### Location
UserGuide.md - Status section

### Suggested Improvement
Add example: `edit 1 s/pending` or `edit 1 s/COMPLETED` to demonstrate case-insensitivity.

---

## Bug #8: Tag Group Names Are Case-Sensitive But Not Clearly Documented
**Type:** `type.DocumentationBug`  
**Severity:** `severity.Low`

### Description
The UG mentions tag group names are case-sensitive, but this could be easily missed. Given that roles are case-insensitive, users might expect consistency.

### Location
UserGuide.md - Creating a Tag Group section

### Suggested Improvement
Add a warning box or more prominent note about case-sensitivity, and explain why this design choice was made (or consider making it case-insensitive for consistency).

---

## Bug #9: No Validation for Unreasonably Large Tag Groups
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.VeryLow`

### Description
Users can create an unlimited number of tag groups, potentially leading to memory issues or cluttered data.

### Steps to Reproduce
1. Create 1000+ tag groups using a script

### Expected
Either impose a reasonable limit (e.g., 50-100 tag groups) or document that there's no limit.

### Actual
(Need to test - likely no limit)

---

## Bug #10: Filter Command With No Parameters Shows All Persons
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Low`

### Description
According to the UG, "If no parameters are provided, no filtering will occur." This means `filter` with no parameters is effectively the same as doing nothing, which could confuse users who expect an error message.

### Steps to Reproduce
1. Execute: `filter`

### Expected
Error message: "At least one filter parameter must be provided."

### Actual
No filtering occurs (no error, no change).

### Justification
Feature flaw - command should either do something useful or provide clear feedback that it requires parameters.

---

## Bug #11: Delete Command Doesn't Confirm for Destructive Action
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Medium`

### Description
The delete command immediately deletes a contact without confirmation. For a property agent managing important client data, accidental deletion could be costly.

### Steps to Reproduce
1. Execute: `delete 1`

### Expected
Either: (1) Add confirmation prompt, or (2) Implement undo functionality, or (3) Document this as a known limitation with warning.

### Actual
Immediate deletion without confirmation.

### Justification
Feature flaw - doesn't match target user needs where accidental deletion of client data could be problematic.

---

## Bug #12: Clear Command Doesn't Confirm Despite Being Irreversible
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.High`

### Description
The UG explicitly states clear command is "irreversible" but there's no confirmation prompt. This is a major usability flaw for property agents who could accidentally lose all client data.

### Steps to Reproduce
1. Execute: `clear`

### Expected
Confirmation prompt: "Are you sure you want to delete all contacts? This action cannot be undone. (y/n)"

### Actual
Immediate deletion of all data without confirmation.

### Justification
Feature flaw - critical safety feature missing for destructive operation on valuable business data.

---

## Bug #13: Find Command Only Searches Names, Not Other Fields
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Medium`

### Description
The find command only searches person names. Property agents might want to search by phone, email, address, role, or tags to find clients. This limitation reduces the feature's usefulness.

### Steps to Reproduce
1. Add a person with phone "91234567"
2. Execute: `find 91234567`

### Expected
Either: (1) Find the person by phone number, or (2) Document that find only searches names and provide alternative commands for other fields.

### Actual
No results (only searches names).

### Justification
Feature flaw - incomplete search functionality doesn't fully meet property agent needs.

---

## Bug #14: No Duplicate Detection for Names
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Medium`

### Description
The system allows duplicate names (only phone and email must be unique). While technically different people can share names, for property agents this could lead to confusion. At minimum, a warning should be shown.

### Steps to Reproduce
1. Execute: `add n/John Doe p/98765432 e/john1@example.com a/123 Street`
2. Execute: `add n/John Doe p/98765433 e/john2@example.com a/456 Street`

### Expected
Either: (1) Prevent duplicate names, or (2) Show warning when adding duplicate name, or (3) Document this behavior clearly.

### Actual
Allows duplicate names without warning.

### Justification
Feature flaw - could lead to confusion when managing clients with similar names.

---

## Bug #15: Stats Command Shows "No Status" Count But Can't Filter By It
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Low`

### Description
The stats command shows count of contacts with "No Status", but there's no way to filter for contacts without a status using the filter command.

### Steps to Reproduce
1. Execute: `stats` (shows "No Status: X contacts")
2. Try to filter for these: `filter s/` or similar

### Expected
Provide a way to filter contacts without status, or document that this isn't possible.

### Actual
No way to filter for "No Status" contacts.

---

## Bug #16: Tag Groups Can't Be Renamed
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Low`

### Description
Once a tag group is created, it can only be deleted (if not in use). There's no command to rename a tag group. If a user makes a typo or wants to change the naming convention, they must delete and recreate.

### Steps to Reproduce
1. Create tag group: `tg PropertyTipe` (typo)
2. Try to rename it to `PropertyType`

### Expected
Provide rename command or document this limitation.

### Actual
No rename functionality.

---

## Bug #17: Edit Command With Invalid Index Shows Generic Error
**Type:** `type.FunctionalityBug`  
**Severity:** `severity.VeryLow`

### Description
When using an invalid index with edit command (e.g., negative number, zero, non-integer), the error message may not be as helpful as it could be.

### Steps to Reproduce
1. Execute: `edit 0 n/John`
2. Execute: `edit -1 n/John`
3. Execute: `edit abc n/John`

### Expected
Clear error messages for each case:
- "Index must be a positive integer"
- "Index must be a positive integer"  
- "Invalid command format"

### Actual
(Need to test actual messages)

---

## Bug #18: Help Window Minimization Issue Not Documented in UG
**Type:** `type.DocumentationBug`  
**Severity:** `severity.VeryLow`

### Description
The known issue about help window minimization is documented in the UG's "Known Issues" section, but users might not find it when they encounter the problem.

### Location
UserGuide.md - Known Issues section

### Suggested Improvement
Consider fixing the issue rather than documenting it, or make the known issues section more prominent.

---

## Bug #19: Multiple Screens Issue Not Well Documented
**Type:** `type.DocumentationBug`  
**Severity:** `severity.VeryLow`

### Description
The known issue about multiple screens requires users to manually delete preferences.json. This is not user-friendly for non-technical users.

### Location
UserGuide.md - Known Issues section

### Suggested Improvement
Either fix the issue or provide a command to reset window position.

---

## Bug #20: Role Validation Inconsistent With Name Validation
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.VeryLow`

### Description
Role allows spaces, hyphens, and underscores (except as first character), while Name only allows alphanumeric and spaces. This inconsistency might confuse users.

### Example
- `r/Property-Manager` is valid (role with hyphen)
- `n/John-Doe` is INVALID (name with hyphen)

### Expected
Document this difference clearly, or make validation consistent.

### Actual
Inconsistent validation between similar fields.

---

## Bug #21: Email Domain Must End With 2+ Character TLD
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.VeryLow`

### Description
Email validation requires domain to end with 2+ character TLD. While this matches most cases, some new TLDs are longer and some country codes are 2 characters. This is technically correct but could be documented.

### Code Location
`Email.java` - DOMAIN_LAST_PART_REGEX

### Expected
Document email format requirements clearly in error messages.

### Actual
Technical regex-based validation that users might not understand.

---

## Bug #22: No Bulk Operations Support
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Medium`

### Description
Property agents might need to perform bulk operations (e.g., delete multiple contacts, change status for multiple contacts). Currently, each operation must be done individually.

### Example Use Case
- Mark all contacts with role "Buyer" as "Completed"
- Delete all contacts with status "Completed" from last year

### Expected
Provide bulk operation commands or document this as a future enhancement.

### Actual
No bulk operation support.

---

## Bug #23: No Export/Import Functionality
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Low`

### Description
Property agents might want to export contact lists to CSV for reporting, or import contacts from other systems. Currently, only JSON editing is possible (which is not user-friendly).

### Expected
Provide export/import commands for common formats (CSV, VCF).

### Actual
Only manual JSON file editing.

---

## Bug #24: UI May Not Handle Very Long Field Values Well
**Type:** `type.FeatureFlaw`  
**Severity:** `severity.Low`

### Description
The UI might not gracefully handle very long addresses, names, or email addresses in the display. Text might be truncated without indication or overflow the display area.

### Steps to Reproduce
1. Add person with very long address (500+ characters)
2. Check how it displays in the UI

### Expected
Either: (1) Truncate with ellipsis and show full text on hover, or (2) Impose reasonable field length limits.

### Actual
(Need to test UI behavior)

---

## Bug #25: Date/Time Stamp Mentioned But Not Visible
**Type:** `type.DocumentationBug`  
**Severity:** `severity.VeryLow`

### Description
The DG mentions "Timestamp in the status bar is updated" after operations, but the UG doesn't explain what this timestamp represents or where to find it.

### Location
DeveloperGuide.md - Manual testing section

### Expected
Either document the timestamp feature in UG or remove references to it if not user-visible.

---

## Summary Statistics

### By Severity
- `severity.High`: 1 bug
- `severity.Medium`: 7 bugs
- `severity.Low`: 10 bugs
- `severity.VeryLow`: 7 bugs

### By Type
- `type.FunctionalityBug`: 3 bugs
- `type.FeatureFlaw`: 17 bugs
- `type.DocumentationBug`: 5 bugs

---

## Testing Notes

Many of these bugs were identified through:
1. Code review of validation logic
2. Analysis of UG/DG documentation
3. Comparison with target user needs (property agents)
4. Checking for defensive programming practices

Some bugs marked "(Need to test)" require actual execution of the JAR file to confirm behavior.

## Recommendations for Priority Fixes

**Critical (Before PE):**
1. Bug #12 - Add confirmation to clear command
2. Bug #11 - Add confirmation or undo for delete command
3. Bug #3 - Fix case-insensitive email duplicate check

**High Priority:**
4. Bug #1 - Improve phone number validation
5. Bug #13 - Enhance search functionality
6. Bug #14 - Handle duplicate names better

**Medium Priority:**
7. Documentation improvements (Bugs #7, #8, #18, #19, #25)
8. Consistency fixes (Bug #20)

**Low Priority:**
9. UI/UX enhancements (Bugs #5, #6, #24)
10. Feature additions (Bugs #16, #22, #23)

