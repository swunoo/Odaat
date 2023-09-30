CREATE TYPE ThemeType AS ENUM (
    'PROJECT', 'ROUTINE'
);

ALTER TABLE task
RENAME COLUMN is_routine TO is_routine_task;
ALTER TABLE task
ADD COLUMN start_time TIME;
ALTER TABLE task
ADD COLUMN end_time TIME;

ALTER TABLE routine
RENAME COLUMN repeated_days TO repeated_on;
ALTER TABLE routine
ADD COLUMN start_time TIME;
ALTER TABLE routine
ADD COLUMN end_time TIME;

ALTER TABLE theme
DROP COLUMN created_at;
ALTER TABLE theme
DROP COLUMN image;
ALTER TABLE theme
ADD COLUMN type ThemeType;
ALTER TABLE theme
ADD COLUMN description TEXT;
ALTER TABLE theme
ADD COLUMN img_name VARCHAR(128);
ALTER TABLE theme
ADD COLUMN started_at DATE;
ALTER TABLE theme
ADD COLUMN completed_at DATE;

ALTER TABLE project
RENAME COLUMN estimated_time TO time_estimated;
ALTER TABLE project
ADD COLUMN deadline DATE;

DROP TABLE image;