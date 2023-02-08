<b>Title and Purpose</b>
<br/> Title: WGU Scheduling Application
<br/> Purpose: To allows users to schedule appointments with customer and edit existing appointments, along with additional reporting functionality.

<b>Program Info</b>
<br/> Author: Jeremy Varkey
<br/> Contact: jvarke1@wgu.edu
<br/> Information: Student at Western Governors University
<br/> Student ID: 001460735
<br/> Application Version: V1.0
<br/> Date: 02.08.2023

<b>IDE Info</b>.
<br/> IDE: IntelliJ Community 2021.01.3
<br/> JDK: Java SE 11.0.11
<br/> JavaFX: JavaFX-SDK-17.0.1

<b>Directions</b>
<br/> As program begins, please input a valid username and password combination. From there, the user will be taken to the main screen and can do all of the program's functionality including adding/modifying/deleting appointments, adding/modifying/deleting customers, and seeing selected reports.

<b>Additional Report</b>
<br/> The additional report is the number of appointments by Type and Second Division Level. The purpose of this report is to see concentration of type of appointment by location, so that we can see what areas attract what types of appointments, and where we should perhaps align headcount.
<br/>
<br/> As an example, if the organization sees that we have a high concentration of Customer Intake appointments in the UK, we should align Sales Headcount resources to that location.

<b>Lambdas</b>
<br/> Lambda 1: Located in Controller.AddCustomer. Initialize Function Line 144. The purpose of the lambda is to add a Listener on the Country Selection ComboBox to change the contents of the Second Division list, initially being set as empty when a new Country is selected.
<br/> Lambda 2: Located in Controller.MainMenu. Initialize Function line 263. The purpose of the lambda is to add the Logout button's functionality upon initialize, making it easier for the user to close the program.

<b>MySQL</b>
<br/> mysql-connector-java-8.0.25