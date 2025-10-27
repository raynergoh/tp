---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# TrackerGuru User Guide
TrackerGuru is a **keyboard-focused desktop app for property agents** to efficiently manage their contacts. It is designed for users who want to complete contact management tasks faster than conventional point-and-click tools.

**Target users and assumptions:**
* Property agents who need to track a variety of contact roles such as *Buyer*, *Seller*, *Landlord* etc.
* Users are assumed to be comfortable typing fast.
* No prior knowledge of programming or Java is required, except following download instructions.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start
### Step 1: Check Java version
TrackerGuru requires **Java 17** or above installed on your computer. (See [FAQ](#faq) for instructions on how to check your Java version)

### Step 2: Download the app
Download the latest TrackerGuru `.jar` file [here](https://github.com/AY2526S1-CS2103T-F15b-3/tp/releases).

### Step 3: Create your app's home folder
Move the `.jar` file into any folder. This will be your _home folder_.

### Step 4: Run the app
1. Open a command terminal. `cd /path/to/your/home/folder`
2. Run the app. `java -jar addressbook.jar`
3. A [GUI](#gui) like this should appear in a few seconds with sample data
   ![Ui](images/Ui.png)

### Step 5: Try basic commands
 In the command box, try any of the following commands. Press Enter to execute.

 | Command | You Should Expect To                                                                   |
 |---------|----------------------------------------------------------------------------------------|
 | `list` | List all contacts                                                                      |
 | `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` | Add a contact `John Doe` with his specified fields                                     |
 | `delete 3` | Delete the 3rd contact displayed.                                                      |
 | `clear` | Delete **all contacts** in the Address Book <br> **⚠️:** _This action is irreversible!_ |
 | `exit` | Exit the TrackerGuru application                                                       |
_Refer to the [Features](#features) section below for more details and commands_

### Step 6: Help Guide

To access this User Guide anytime from the app, use the **`help`** command. A help window will open up, containing the User Guide.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. `n/NAME p/PHONE_NUMBER` and `p/PHONE_NUMBER n/NAME` are both acceptable.

* Tags should either be in the format:
  - `t/GROUP.VALUE` where both `GROUP` and `VALUE` are alphanumeric 
  - a simple Tag `t/TAG` with an alphanumeric string

* Commands that do not require parameters (such as help, list, exit, and clear) will ignore any additional input.
  e.g. `help 123` will be interpreted as `help`.

* When using the PDF version of this document, note that copying multiple-line commands may remove spaces around line breaks. Please check that spaces are correct before pasting into the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/ROLE]…​ [s/STATUS] [t/TAG]…`

<box type="tip" seamless>

**Note:** 
* No two persons can share the same `NAME`, `PHONE_NUMBER` or `EMAIL`.
* You can add multiple roles by repeating `r/ROLE` (e.g., `r/Buyer r/Investor`).
* Status must be either **Pending** or **Completed** (case-insensitive). Each person can have only one status.
* Status is optional. If not specified, the person will have no status.
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe p/12345678 e/betsycrowe@example.com a/Betsy street, block 123 r/Seller s/Completed t/friend`
* `add n/Alex Tan p/87654321 e/alex@example.com a/Blk 456 r/Buyer r/Investor s/Pending`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Listing all Tag Groups : `tg`

Lists all TagGroups already created

Format: `tg`

### Create a Tag Group : `tg`

Creates a new TagGroup

Format: `tg TagGroup`

Examples:
* `tg PropertyType`

### Delete a Tag Group : `dtg`

Deletes an existing TagGroup

Format: `dtg TagGroup` 

Examples: 
* `dtg PropertyType`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/ROLE]…​ [s/STATUS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. 
  - `INDEX` refers to the number shown beside each person in the displayed list.
  - `INDEX` **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values for `name`, `phone`, `email`, and `address` will be updated to the input values.
* When editing roles, the existing roles of the person will be removed (i.e., adding roles is not cumulative). You can specify multiple roles using multiple `r/` prefixes.
* Status must be either **Pending** or **Completed** (case-insensitive). Editing status will replace the existing status.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person's tags by typing `t/` without specifying any tags after it.
* You can remove all the person's roles by typing `r/` without specifying any role after it.
* You can remove a person's status by typing `s/` without specifying any status after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 1 s/Completed` Edits the status of the 1st person to `Completed`. If the person did not have a status, it will be added.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 3 r/Buyer r/Landlord` Replaces all existing roles of the 3rd person with `Buyer` and `Landlord`.
*  `edit 4 s/` Removes the status from the 4th person.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Filtering persons by Role, Status, Tag Group: `filter`

Filters the contact list to show only persons matching the specified parameters.

Format: `filter [r/ROLE]…​ [s/STATUS]…​ [tg/TAG_GROUP]…​`

* The search is **case-sensitive**. e.g. `buyer` will not match `Buyer`
* You can filter with any combination of: **Role(s)**, **Status(es)**, **TagGroup(s)**
* You may specify multiple Roles, Statuses, and Tag Groups
* A person will be shown if they match **any** of the specified parameters 
* If no parameters are provided, no filtering will occur

Examples:
* `filter r/buyer` returns all persons with the role **buyer**
* `filter s/pending s/completed` returns all persons whose status is **pending** or **completed**
* `filter r/buyer s/completed tg/price` returns all persons who are **buyers**, or whose status is **completed**, or has a tag whose Tag Group is **price**

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

[//]: # (### Archiving data files `[coming in v2.0]`)

[//]: # ()
[//]: # ()
[//]: # (_Details coming soon ..._)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I check my Java version?<br>
**A**: To verify your Java version:
1. Open a terminal and enter `java -version`
2. You should see an output similar to:
   ```
   java version "17.0.x"  // or any higher version
   Java(TM) SE Runtime Environment (build 17.0.x+xx)
   Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+xx, mixed mode, sharing)
   ```
3. **If the command is not recognised** or the version is less than 17: Follow this [guide](https://se-education.org/guides/tutorials/javaInstallation.html) for installation.
4. After installation, type `java -version` to verify the correct version.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app on the new computer. Then, locate the new TrackerGuru home folder and replace its data folder with the data folder from your old computer.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file (in the same directory as the JAR file) created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/ROLE]…​ [s/STATUS] [t/TAG] [t/GROUP.VALUE]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 r/Buyer r/Investor s/Pending t/priority t/propertyType.HDB`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [r/ROLE]…​ [s/STATUS] [t/TAG] [t/GROUP.VALUE]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com s/Completed`
**Filter** | `filter [r/ROLE]…​ [s/STATUS]…​ [tg/TAG_GROUP]…​`<br> e.g., `filter r/buyer s/pending tg/price`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Create Tag Group**| `tg GROUP` <br> e.g., `tg PropertyType`
**Delete Tag Group**| `dtg GROUP` <br> e.g., `dtg PropertyType`
**List Tag Groups**| `tg`
**List**   | `list`
**Help**   | `help`

## Glossary
### Java
Java is a programming platform used to run applications like TrackerGuru. TrackerGuru requires Java 17 or above.

### GUI
GUI Stands for Graphical User Interface. It is the visual part of TrackerGuru that you interact with using windows, buttons, and text fields and displays all your contacts.

### Role
A Role categorises the type of contact in your address book. Each contact can have **multiple roles**.

Common roles for property agents include:
- **Buyer**: Someone looking to purchase property
- **Seller**: Someone looking to sell property
- **Landlord**: Property owner who rents out properties
- **Tenant**: Someone looking to rent property
- **Investor**: Someone interested in property investment

Roles are **case-sensitive** (e.g., "buyer", "Buyer", and "BUYER" are not treated the same). You can use any text as a role to fit your needs.

### Status
A Status tracks the progress or state of your interaction with a contact. Each contact can have **only one status** (or none).

**Valid status values:**
- **Pending**: Initial contact or ongoing negotiation
- **Completed**: Transaction successfully finished

Status values are **case-insensitive** (e.g., "pending", "Pending", and "PENDING" are all valid). Only these two values are allowed.
