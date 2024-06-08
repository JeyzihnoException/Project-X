INSERT INTO role (uuid, name) VALUES
                                  ('ad6774cd-4ea7-4658-ad1a-794d7035a5d3', 'USER'),
                                  ('ad47a176-fe7d-4c55-872a-be1c2732f9ee', 'ADMIN'),
                                  ('bd47a176-fe7d-4c55-872a-be1c2732f9ee', 'COMMUNITY_ADMIN');

INSERT INTO privilege (uuid, name) VALUES
                                       ('8f380efb-2b22-4a76-ad86-faf94cd618b0', 'SEND_MESSAGE'),
                                       ('dcbf80cb-e5f8-495b-a266-b8960393a114', 'ADD_FRIEND'),
                                       ('0fbfcedf-0b94-4c1e-b5d1-d762e27577e8', 'EDIT_SELF_PAGE'),
                                       ('f0c59a5b-221d-46da-ac36-ab811f1c9ffe', 'CREATE_COMMUNITY'),
                                       ('8627b55d-820f-41ce-920d-3fa9fe3ada2c', 'USER_DELETE'),
                                       ('7627b55d-820f-41ce-920d-3fa9fe3ada2c', 'ADMIN_PANEL');

INSERT INTO role_privileges (privileges_uuid, role_uuid) VALUES
                                                       ('8f380efb-2b22-4a76-ad86-faf94cd618b0', 'ad6774cd-4ea7-4658-ad1a-794d7035a5d3'),
                                                       ('dcbf80cb-e5f8-495b-a266-b8960393a114', 'ad6774cd-4ea7-4658-ad1a-794d7035a5d3'),
                                                       ('0fbfcedf-0b94-4c1e-b5d1-d762e27577e8', 'ad6774cd-4ea7-4658-ad1a-794d7035a5d3'),
                                                       ('f0c59a5b-221d-46da-ac36-ab811f1c9ffe', 'ad6774cd-4ea7-4658-ad1a-794d7035a5d3'),
                                                       ('8f380efb-2b22-4a76-ad86-faf94cd618b0', 'ad47a176-fe7d-4c55-872a-be1c2732f9ee'),
                                                       ('dcbf80cb-e5f8-495b-a266-b8960393a114', 'ad47a176-fe7d-4c55-872a-be1c2732f9ee'),
                                                       ('0fbfcedf-0b94-4c1e-b5d1-d762e27577e8', 'ad47a176-fe7d-4c55-872a-be1c2732f9ee'),
                                                       ('f0c59a5b-221d-46da-ac36-ab811f1c9ffe', 'ad47a176-fe7d-4c55-872a-be1c2732f9ee'),
                                                       ('8627b55d-820f-41ce-920d-3fa9fe3ada2c', 'ad47a176-fe7d-4c55-872a-be1c2732f9ee'),
                                                       ('7627b55d-820f-41ce-920d-3fa9fe3ada2c', 'ad47a176-fe7d-4c55-872a-be1c2732f9ee');
