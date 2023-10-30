CREATE TABLE buckets (
    id UUID NOT NULL,
    name VARCHAR(255),
    object_count INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE instances (
    id UUID NOT NULL,
    created_on TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE jobs (
    id UUID NOT NULL,
    created_on TIMESTAMP,
    reason VARCHAR(255),
    status INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE s3_objects (
    id UUID NOT NULL,
    bucket_id UUID,
    bucket_name VARCHAR(255),
    e_tag VARCHAR(255),
    object_key VARCHAR(255),
    object_size BIGINT,
    storage_class VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO instances (id, created_on) VALUES
  (gen_random_uuid(), CURRENT_TIMESTAMP),
  (gen_random_uuid(), CURRENT_TIMESTAMP),
  (gen_random_uuid(), CURRENT_TIMESTAMP),
  (gen_random_uuid(), CURRENT_TIMESTAMP),
  (gen_random_uuid(), CURRENT_TIMESTAMP),
  (gen_random_uuid(), CURRENT_TIMESTAMP),
  (gen_random_uuid(), CURRENT_TIMESTAMP),
  (gen_random_uuid(), CURRENT_TIMESTAMP);

INSERT INTO jobs (id, created_on, status, reason) VALUES
  (gen_random_uuid(), CURRENT_TIMESTAMP, 1, ''),
  (gen_random_uuid(), CURRENT_TIMESTAMP, 0, ''),
  (gen_random_uuid(), CURRENT_TIMESTAMP, 1, ''),
  (gen_random_uuid(), CURRENT_TIMESTAMP, 2, 'unknown error'),
  (gen_random_uuid(), CURRENT_TIMESTAMP, 1, ''),
  (gen_random_uuid(), CURRENT_TIMESTAMP, 0, ''),
  (gen_random_uuid(), CURRENT_TIMESTAMP, 1, ''),
  (gen_random_uuid(), CURRENT_TIMESTAMP, 0, ''),
  (gen_random_uuid(), CURRENT_TIMESTAMP, 2, 'unknown error');

INSERT INTO buckets (id, name, object_count) VALUES
  (gen_random_uuid(), 'Bucket 1', 5);

INSERT INTO s3_objects (id, bucket_id, bucket_name, object_key, e_tag, object_size, storage_class) VALUES
  (gen_random_uuid(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object1.txt', 'etag1', 104, 'STANDARD'),
  (gen_random_uuid(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object2.txt', 'etag1', 1024, 'STANDARD'),
  (gen_random_uuid(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object3.txt', 'etag1', 152, 'STANDARD'),
  (gen_random_uuid(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object4.txt', 'etag1', 1324, 'STANDARD'),
  (gen_random_uuid(), (SELECT id FROM buckets WHERE name = 'Bucket 1'), 'Bucket 1', 'object5.txt', 'etag1', 124, 'STANDARD');
