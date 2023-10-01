-- Deleting duplicate-columns from project and routine.
-- Maybe this wouldn't have been necessary if I didn't use INHERIT theme.

ALTER TABLE project NO INHERIT theme;
ALTER TABLE routine NO INHERIT theme;

ALTER TABLE project
RENAME COLUMN id to theme_id;

ALTER TABLE project
DROP COLUMN name, program, time_spent, type, description, img_name, started_at, completed_at;

ALTER TABLE routine
RENAME COLUMN id to theme_id;

ALTER TABLE routine
DROP COLUMN name, program, time_spent, type, description, img_name, started_at, completed_at;