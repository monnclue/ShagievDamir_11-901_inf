# BCRYPT

* original - `qwerty007`

* hashed - `$2a$10$YT4skQkJ84utGSRs5uiIo.5GFTSpxVqKtp6ywpu7HoFTnVVKRMVfu`

```

[$2a$][cost]$[22 character salt][31 character hash]

[$2a$][10]$[YT4skQkJ84utGSRs5uiIo.][5GFTSpxVqKtp6ywpu7HoFTnVVKRMVfu]
```

## Принцип работы

### Хеширование

```
qwerty007

hash = hash(password + salt(random))

hash = hash(qwerty007 + YT4skQkJ84utGSRs5uiIo.)

hash = 5GFTSpxVqKtp6ywpu7HoFTnVVKRMVfu

result = $2a$10$YT4skQkJ84utGSRs5uiIo.5GFTSpxVqKtp6ywpu7HoFTnVVKRMVfu
```

### Проверка

```
sidikov.marsel@gmail.com
qwerty007

Ищем в базе пользователя с email = sidikov.marsel@gmail.com
Смотрим его хешированный пароль - $2a$10$YT4skQkJ84utGSRs5uiIo.5GFTSpxVqKtp6ywpu7HoFTnVVKRMVfu

Нам подали на вход пароль qwerty007
Соль использовалась - YT4skQkJ84utGSRs5uiIo.

Хешируем заново с этой солью, проверяем совпали хеш или нет.
```
