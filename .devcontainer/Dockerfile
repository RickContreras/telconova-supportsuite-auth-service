FROM openjdk:21-jdk-slim

# Instalar dependencias necesarias
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
        postgresql-client \
        git \
        maven \
        sudo \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*

# Crear el usuario vscode con permisos adecuados
RUN useradd -ms /bin/bash vscode \
    && mkdir -p /workspace \
    && chown -R vscode:vscode /workspace \
    && usermod -aG sudo vscode \
    && echo "vscode ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

# Establecer el usuario vscode como predeterminado
USER vscode

# Establecer el directorio de trabajo
WORKDIR /workspace