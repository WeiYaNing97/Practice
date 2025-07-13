# 重点：

### 启动minio
```bash
start cmd /k "d: && cd D:\Program Files\minio\bin && .\minio.exe server D:\Program Files\minio\data --console-address "127.0.0.1:9000" --address "127.0.0.1:9005""
```

### 启动MC
```bash 
start cmd /k "d: && cd D:\Program Files\minio\bin && mc alias set myminio http://localhost:9005 admin 12345678"
```
### 修改桶的权限

### 根据API 和 WEB 来修改 配置文件信息
```bash
    MinIO Object Storage Server
    Copyright: 2015-2025 MinIO, Inc.
    License: GNU AGPLv3 - https://www.gnu.org/licenses/agpl-3.0.html
    Version: RELEASE.2025-05-24T17-08-30Z (go1.24.3 windows/amd64)
    
    API: http://127.0.0.1:9005
    RootUser: admin
    RootPass: 12345678
    
    WebUI: http://127.0.0.1:9000
    RootUser: admin
    RootPass: 12345678
    
    CLI: https://min.io/docs/minio/linux/reference/minio-mc.html#quickstart
    $ mc alias set 'myminio' 'http://127.0.0.1:9005' 'admin' '12345678'
    
    Docs: https://docs.min.io
    
    
    
    
    # 配置文件 minio 配置信息
    minio:
        endpoint: http://127.0.0.1:9005
        accessKey: admin
        secretKey: 12345678
        bucketName: mybucket
```