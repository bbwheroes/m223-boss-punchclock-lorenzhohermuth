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

#  systemd.services.my-docker-compose = {
#    script = ''
#      docker compose -f ./.devcontainer/docker-compose.yml up db pgadmin
#    '';
#    wantedBy = ["multi-user.target"];
#    # If you use docker
#    after = ["docker.service" "docker.socket"];
#  };
  POSTGRES_PASSWORD = "postgres";
  POSTGRES_USER = "postgres";
  POSTGRES_DB = "postgres";
  POSTGRES_HOSTNAME = "localhost:5432";

  shellHook = ''
      docker compose -f .devcontainer/docker-compose.yml up db pgadmin -d

      trap 'docker compose -f .devcontainer/docker-compose.yml down' EXIT
  '';
}
