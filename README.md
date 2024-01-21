## Running with Skaffold

Create `.env` file with

```
OAUTH2_GOOGLE_CLIENT_ID=...
OAUTH2_GOOGLE_CLIENT_SECRET=...
```

```shell
kubectl create secret generic try-spring-oauth2 --from-env-file=.env
```