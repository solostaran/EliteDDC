# EliteDDC
Elite:Dangerous Data Collection (Spring, Hibernate, WebService)

This WebService provide useful entries and a database to store and explore data that you can manually collect within the game Elite:Dangerous (https://www.elitedangerous.com/)

Compilation:
maven compile package -e : generate a single executable jar file.

Download:
Here is a compiled version with a small H2 database : https://www.dropbox.com/s/5ptwxk6764ukk97/EDDC.zip?dl=0

Launch:
Console launch if you want a trace (EliteWebService.bat launcher provided for windows)
When the server is launched, you can access a Usage html page here : http://{localhost/serverhost}:8080/

Usage:
I provided some html pages (Thymeleaf views) which are not optimized, but it is a start : http://{serverhost}:8080/html/menu

Database:
By default, without a valid 'dbconfig.json' file, it creates a local H2 database in the execution directory. I tested it with MySql and H2, it could work with SQLite but not a this time and H2 has better performances in my tests.
I provided some 'dbconfig.json' files as database configuration examples (H2, MySql and SQLite).



