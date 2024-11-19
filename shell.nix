{ pkgs ? import <nixpkgs> {} }:
pkgs.mkShell {
  # nativeBuildInputs is usually what you want -- tools you need to run
  nativeBuildInputs = with pkgs.buildPackages; [ 
    quarkus
    zulu17
    maven
    systemd
    systemd
  ];

  POSTGRES_PASSWORD = "postgres";
  POSTGRES_USER = "postgres";
  POSTGRES_DB = "postgres";
  POSTGRES_HOSTNAME = "localhost:5432";

  shellHook = ''
      docker compose -f .devcontainer/docker-compose.yml up db pgadmin -d

      trap 'docker compose -f .devcontainer/docker-compose.yml down' EXIT
  '';
}
