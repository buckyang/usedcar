insert into brand (brand_initial, brand_name, brand_description) values('D', '大众', '大众');
insert into brand (brand_initial, brand_name, brand_description) values('F', '福特', '福特');
insert into series (series_initial, series_name, type, brand_id) values('G', '高尔夫', 0, 1);
insert into series (series_initial, series_name, type, brand_id) values('M', '迈腾', 0, 1);
insert into series (series_initial, series_name, type, brand_id) values('F', '福克斯', 0, 2);
insert into series (series_initial, series_name, type, brand_id) values('M', '蒙迪欧', 0, 2);
insert into model (sub_series, model_name, displacement, transmission_type, launch_year, driven_type, series_id)
            values ('2012 款', '1.4T 自动豪华型', '1400', '0', '2012', '0', 1);
insert into model (sub_series, model_name, displacement, transmission_type, launch_year, driven_type, series_id)
            values ('2014 款', '1.4T 自动豪华型', '1400', '0', '2014', '0', 1);
insert into model (sub_series, model_name, displacement, transmission_type, launch_year, driven_type, series_id)
            values ('2012 款', '1.4T 手自一体 豪华型', '1400', '0', '2012', '0', 2);
insert into model (sub_series, model_name, displacement, transmission_type, launch_year, driven_type, series_id)
            values ('2011 款', '2.0T 手自一体 豪华型', '20000', '0', '2011', '0', 2);
insert into model (sub_series, model_name, displacement, transmission_type, launch_year, driven_type, series_id)
            values ('2012 款', '1.6L 手动风尚型', '1600', '1', '2012', '1', 3);
insert into model (sub_series, model_name, displacement, transmission_type, launch_year, driven_type, series_id)
            values ('2011 款', '2.0 手动时尚型', '2000', '1', '2011', '1', 3);
insert into model (sub_series, model_name, displacement, transmission_type, launch_year, driven_type, series_id)
            values ('2012 款', '1.6 手动时尚型', '1600', '1', '2012', '1', 4);
insert into model (sub_series, model_name, displacement, transmission_type, launch_year, driven_type, series_id)
            values ('2011 款', '2.0 手动时尚型', '2000', '1', '2011', '1', 4);