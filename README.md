
* Install Chrome extension: https://chrome.google.com/webstore/detail/custom-javascript-for-web/poakhlngfciodnhlhhgnaaelnpjljija?hl=en

* Open web console and add filter sc-cjs: injected.js





curl -o test.mp3 'https://cf-hls-media.sndcdn.com/media/0/31762/X8CS2k3gj9mG.128.mp3?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiKjovL2NmLWhscy1tZWRpYS5zbmRjZG4uY29tL21lZGlhLyovKi9YOENTMmszZ2o5bUcuMTI4Lm1wMyIsIkNvbmRpdGlvbiI6eyJEYXRlTGVzc1RoYW4iOnsiQVdTOkVwb2NoVGltZSI6MTUxMTM1NjQ3MX19fV19&Signature=zw1CQXX0joBzCxNZl0ei~Lj~U1M8zZtuHGi4Jy92mBVa5jLgtVvdUFIpreiCNckSS43LR79-65fyWDHl65XM7c5Q~kgtKpA6s3M2pP8kQB9jHVPmTxPRH155uhn8~vhi1TZmvaVe954t7gd-hTzaM9bj7cOjxfIXlIfxk5BcwIF~~~ekb0PvMJtAdPZYU46KuXVXACkUdsDbs62zrxXnpLGgl7zCyTsHZwzF6xaKNE8n7A912VMhPKV61M1UP8SLqHuI4knMQO6Icre0NT6~2~goVqePGX67I4FBa9XbHwk2eX6SK2BWrYbQ2bj7dRZOD7gsDmJT15DupuphxCUeHQ__&Key-Pair-Id=APKAJAGZ7VMH2PFPW6UQ' \
-XGET \
-H 'Referer: https://soundcloud.com/' \
-H 'Origin: https://soundcloud.com' \
-H 'Host: cf-hls-media.sndcdn.com' \
-H 'Accept: */*' \
-H 'Connection: keep-alive' \
-H 'Accept-Language: en-us' \
-H 'Accept-Encoding: br, gzip, deflate' \
-H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Safari/604.1.38'


curl -o test.opus 'https://cf-hls-opus-media.sndcdn.com/media/292941/384623/X8CS2k3gj9mG.64.opus?Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiKjovL2NmLWhscy1vcHVzLW1lZGlhLnNuZGNkbi5jb20vbWVkaWEvKi8qL1g4Q1MyazNnajltRy42NC5vcHVzIiwiQ29uZGl0aW9uIjp7IkRhdGVMZXNzVGhhbiI6eyJBV1M6RXBvY2hUaW1lIjoxNTExMzU2NjU2fX19XX0_&Signature=cmoFLGxZqmc97sfQQtNw0cUcfq6ktP9oEhwpyrOW~HAINT3XJfy9Z2sk8JgJE1XO8o-RtSiUG9VBmUbGK6GIuhVJ0AYit7MQL1ukW63q8gUFgtHwnNNgw66ASrzmtwQUhfI8RrDp3XW5rfSTnrKMqL6qKfISsmLEG79-hosC83bV8yzbb9BxucRYmom8cwR90tO~5C~hhwR3TMWYqCz6MNJyBoSg35fZ6GblDmXt650c8INTzcQz-NU4sI5UtrRZLz~Q1vwDPtMHUZiKcKfg97afS5zjUir-AvS4dOZ0TdWeWdZIruM3UIpt5AirZrF9QuhqIg55z78ASpkBrixYag__&Key-Pair-Id=APKAJAGZ7VMH2PFPW6UQ' \
-XGET \
-H 'Referer: https://soundcloud.com/' \
-H 'Origin: https://soundcloud.com' \
-H 'Host: cf-hls-opus-media.sndcdn.com' \
-H 'Accept: */*' \
-H 'Connection: keep-alive' \
-H 'Accept-Language: en-us' \
-H 'Accept-Encoding: br, gzip, deflate' \
-H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36'


