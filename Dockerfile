# Sử dụng image maven với JDK 21 để build ứng dụng (giai đoạn build)
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Copy file cấu hình và mã nguồn vào container
COPY pom.xml ./
COPY src ./src

# Tải dependencies và build ứng dụng
RUN mvn clean package -DskipTests

# Tạo image mới với JDK 21 nhẹ hơn chỉ để chạy ứng dụng (giai đoạn runtime)
FROM openjdk:21-jdk-slim

# Thiết lập thư mục làm việc cho ứng dụng
WORKDIR /app

# Copy file JAR từ giai đoạn build sang giai đoạn runtime
COPY --from=build /app/target/*.jar app.jar

EXPOSE 10000

# Khởi chạy ứng dụng và lắng nghe trên cổng PORT
ENTRYPOINT ["java", "-jar", "app.jar"]