if [[ "$1" != "" ]]
then
  if [[ "$1" == "alpha" ]]
  then
    VERSION=$(git tag --sort=-v:refname --merged HEAD | grep -E "^[0-9]+.[0-9]+.[0-9]+.[0-9]+$" | head -1)
    PREVIOUS_VERSION=$(git tag --sort=-v:refname --merged HEAD | grep -E "^[0-9]+.[0-9]+.[0-9]+.[0-9]+$" | head -2 | tail -1)
  else
    VERSION=$(git tag --sort=-v:refname --merged HEAD | grep -E "^[0-9]+.[0-9]+.[0-9]+$" | head -1)
    PREVIOUS_VERSION=$(git tag --sort=-v:refname --merged HEAD | grep -E "^[0-9]+.[0-9]+.[0-9]+$" | head -2 | tail -1)
  fi
else
  echo "Cannot generate ReleaseNote is there are no environment set!"
  exit 1
fi

CHANGES=$(git log --pretty="- %s" $VERSION...$PREVIOUS_VERSION)
message=""
githubReleaseMessage=""
regex="^(ci|docs|feat|fix|refactor|style|test)(\(\w+\))?(:\s.*)+"

while read -r line; do
  action_and_description="${line#* }"
  reference=$(echo "$line" | grep -Eo "$regex")
  message+="$reference $action_and_description see on Jira https://url.atlassian.net/browse/$reference\n"
  githubReleaseMessage+="<a href="https://url.atlassian.net/browse/$reference">$reference</a> $action_and_description <br>"
done <<<"$(git log --pretty="%s" "$VERSION"..."$PREVIOUS_VERSION")"

printf "🎁 Release notes (\`$VERSION\`)\n\nChanges\n$message\nMetadata\nThis version -------- $VERSION\nPrevious version ---- $PREVIOUS_VERSION\nTotal commits ------- $(echo "$CHANGES" | wc -l)" > ../release-notes.txt
echo $githubReleaseMessage # this is for github release ...
