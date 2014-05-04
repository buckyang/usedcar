alter table county change count_sort county_sort int(11);
alter table series drop column series_initial;
alter table series modify column type VARCHAR(10);
alter table model modify column transmission_type VARCHAR(15);
alter table model drop column driven_type;
alter table model add column country;