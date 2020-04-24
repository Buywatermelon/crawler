/* 删除id重复数据，通过新建my_id作为主键id,删除后，将id设立为主键id */
DELETE
FROM
	news
WHERE
	my_id NOT IN (
	SELECT
		dt.min_id
	FROM
	( SELECT MIN( my_id ) AS min_id FROM news GROUP BY id ) dt
	)

