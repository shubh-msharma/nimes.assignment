create table buckets (
            id varchar(255) not null,
            name varchar(255),
            object_count integer,
            primary key (id)
 );

create table instances (
            id varchar(255) not null,
            created_on timestamp,
            primary key (id)
);

create table jobs (
        id varchar(255) not null,
        created_on timestamp,
        reason varchar(255),
        status integer,
        primary key (id)
);

create table s3_objects (
                id varchar(255) not null,
                bucket_id varchar(255),
                bucket_name varchar(255),
                e_tag varchar(255),
                object_key varchar(255),
                object_size bigint,
                storage_class varchar(255),
                primary key (id)
);

INSERT INTO instances (id, created_on) VALUES
  (RANDOM_UUID(),CURRENT_TIMESTAMP),
  (RANDOM_UUID(),CURRENT_TIMESTAMP),
  (RANDOM_UUID(),CURRENT_TIMESTAMP),
  (RANDOM_UUID(),CURRENT_TIMESTAMP),
  (RANDOM_UUID(),CURRENT_TIMESTAMP),
  (RANDOM_UUID(),CURRENT_TIMESTAMP),
  (RANDOM_UUID(),CURRENT_TIMESTAMP),
  (RANDOM_UUID(),CURRENT_TIMESTAMP);


INSERT INTO jobs (id, created_on, status, reason) VALUES
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 1, ''),
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 0, ''),
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 1, ''),
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 2, 'unknown error'),
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 1, ''),
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 0, ''),
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 1, ''),
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 0, ''),
  (RANDOM_UUID(), CURRENT_TIMESTAMP, 2, 'unknown error');


INSERT INTO buckets (id, name, object_count) VALUES
  (RANDOM_UUID(), 'Bucket 1', 5);

INSERT INTO s3_objects (id, bucket_id, bucket_name, object_key, e_tag, object_size, storage_class) VALUES
  (RANDOM_UUID(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object1.txt', 'etag1', 104, 'STANDARD'),
  (RANDOM_UUID(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object2.txt', 'etag1', 1024, 'STANDARD'),
  (RANDOM_UUID(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object3.txt', 'etag1', 152, 'STANDARD'),
  (RANDOM_UUID(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object4.txt', 'etag1', 1324, 'STANDARD'),
  (RANDOM_UUID(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object5.txt', 'etag1', 124, 'STANDARD');