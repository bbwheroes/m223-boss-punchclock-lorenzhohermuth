{ pkgs ? import <nixpkgs> {} }:
pkgs.mkShell {
  nativeBuildInputs = with pkgs.buildPackages; [ 
    quarkus
    zulu17
    maven
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
