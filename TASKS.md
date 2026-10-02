# Minibuild — Task List

- [ ] Parser et valider une coordonnée Gav
    - [ ] Rejeter une coordonnée sans groupe
    - [ ] Rejeter une coordonnée sans artefact
    - [ ] Rejeter une coordonnée sans version
    - [ ] Rejeter une coordonnée avec moins de trois parties
    - [ ] Rejeter une coordonnée avec plus de trois parties

- [ ] Stocker et retrouver un Artifact dans InMemoryStorage
- [ ] Lire les lignes avec BufferedLineReader
- [ ] Parser un fichier de build avec LineBasedPomParser
- [ ] Publier et rechercher un Artifact avec StorageBasedRegistry
- [ ] Résoudre les dépendances transitives avec AllVersionsResolver
- [ ] Orchestrer la construction avec BuildTool