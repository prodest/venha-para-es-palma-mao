# Create release script
#
# prerequisites:
# `sudo yarn global add rimraf conventional-recommended-bump conventional-changelog-cli conventional-github-releaser conventional-commits-detector json`
# or
# `sudo npm i -g rimraf conventional-recommended-bump conventional-changelog-cli conventional-github-releaser conventional-commits-detector json`
#
# `np` with optional argument `patch`/`minor`/`major`/`<version>`
# defaults to conventional-recommended-bump
# and optional argument preset `angular`/ `jquery` ...
# defaults to conventional-commits-detector
#
# For release setup token authentication (https://github.com/conventional-changelog/conventional-github-releaser)
# Suggestion: environment CONVENTIONAL_GITHUB_RELEASER_TOKEN

echo "clean node_modules" &&
rimraf node_modules &&
rimraf package-lock.json &&
echo "git checkout develop" &&
git checkout develop &&
echo "git pull --rebase origin develop" &&
git pull --rebase origin develop &&
echo "git checkout master" &&
git checkout master &&
echo "git pull --rebase origin master" &&
git pull --rebase origin master &&
echo "git rebase develop" &&
git rebase develop &&
echo "yarn install" &&
yarn install --dev &&
echo "tslint" &&
yarn lint &&
echo "unit test" &&
yarn test &&
echo "e2e test" &&
yarn test:e2e &&
echo "save safe package.json" &&
cp package.json _package.json &&
preset=$(conventional-commits-detector) &&
echo "preset: ${2:-$preset}" &&
bump=$(conventional-recommended-bump -p ${2:-$preset}) &&
echo "bump: ${1:-$bump}" &&
npm --no-git-tag-version version ${1:-$bump} &&
echo "generate changelog" &&
conventional-changelog -i CHANGELOG.md -s -p ${2:-$preset} &&
git add CHANGELOG.md &&
version=$(json -f package.json version) &&
echo "version: ${3:-$version}" &&
echo "commit changelog" &&
git commit -m "docs(changelog): v$version" &&
echo "restore safe package.json" &&
mv -f _package.json package.json &&
echo "commit and push tag" &&
npm version ${1:-$bump} -m "chore(release): v%s" &&
git push --follow-tags &&
git checkout develop &&
git merge --no-ff master &&
git push origin develop &&
echo "github release" &&
conventional-github-releaser -p ${2:-$preset}
