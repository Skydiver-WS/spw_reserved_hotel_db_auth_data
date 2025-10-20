{{/*
Common labels
*/}}
{{- define "postgresql.labels" -}}
app: {{ .Values.postgresql.name }}
component: database
chart: {{ .Chart.Name }}-{{ .Chart.Version }}
release: {{ .Release.Name }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "postgresql.selectorLabels" -}}
app: {{ .Values.postgresql.name }}
component: database
{{- end }}