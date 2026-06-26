#!/usr/bin/env bash

# 📊 SCRIPT DE VERIFICACIÓN DE ESTRUCTURA - HotelOps Project
# Ejecutar: bash verify_structure.sh

set -e

BLUE='\033[0;34m'
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

BASE_PATH="app/src/main/java/com/hotelops/app"

echo -e "${BLUE}🏨 Verificación de Estructura - HotelOps${NC}"
echo "=================================================="
echo ""

# Función para contar archivos
count_files() {
    local dir=$1
    local count=$(find "$dir" -type f -name "*.kt" 2>/dev/null | wc -l)
    echo $count
}

# Función para verificar carpeta
verify_dir() {
    local dir=$1
    local name=$2

    if [ -d "$dir" ]; then
        echo -e "${GREEN}✅${NC} $name"
        return 0
    else
        echo -e "${RED}❌${NC} $name - NO EXISTE"
        return 1
    fi
}

# DOMAIN LAYER
echo -e "${BLUE}📦 DOMAIN LAYER${NC}"
verify_dir "$BASE_PATH/domain/models" "  models/"
verify_dir "$BASE_PATH/domain/repositories" "  repositories/"
verify_dir "$BASE_PATH/domain/usecases" "  usecases/"
verify_dir "$BASE_PATH/domain/exceptions" "  exceptions/"

domain_files=$(count_files "$BASE_PATH/domain")
echo -e "  Archivos: ${GREEN}$domain_files${NC}"
echo ""

# DATA LAYER
echo -e "${BLUE}🔄 DATA LAYER${NC}"
verify_dir "$BASE_PATH/data/local/database" "  local/database/"
verify_dir "$BASE_PATH/data/local/dao" "  local/dao/"
verify_dir "$BASE_PATH/data/local/entities" "  local/entities/"
verify_dir "$BASE_PATH/data/remote/api" "  remote/api/"
verify_dir "$BASE_PATH/data/remote/dto" "  remote/dto/"
verify_dir "$BASE_PATH/data/repositories" "  repositories/"
verify_dir "$BASE_PATH/data/mappers" "  mappers/"

data_files=$(count_files "$BASE_PATH/data")
echo -e "  Archivos: ${GREEN}$data_files${NC}"
echo ""

# PRESENTATION LAYER
echo -e "${BLUE}🎨 PRESENTATION LAYER${NC}"
verify_dir "$BASE_PATH/presentation/screens" "  screens/"
verify_dir "$BASE_PATH/presentation/viewmodels" "  viewmodels/"
verify_dir "$BASE_PATH/presentation/components" "  components/"
verify_dir "$BASE_PATH/presentation/navigation" "  navigation/"
verify_dir "$BASE_PATH/presentation/ui/theme" "  ui/theme/"

presentation_files=$(count_files "$BASE_PATH/presentation")
echo -e "  Archivos: ${GREEN}$presentation_files${NC}"
echo ""

# DI LAYER
echo -e "${BLUE}💉 DEPENDENCY INJECTION${NC}"
verify_dir "$BASE_PATH/di/modules" "  modules/"

di_files=$(count_files "$BASE_PATH/di")
echo -e "  Archivos: ${GREEN}$di_files${NC}"
echo ""

# WORKERS & UTILS
echo -e "${BLUE}🔧 WORKERS & UTILS${NC}"
verify_dir "$BASE_PATH/workers" "  workers/"
verify_dir "$BASE_PATH/utils" "  utils/"

workers_files=$(count_files "$BASE_PATH/workers")
utils_files=$(count_files "$BASE_PATH/utils")
echo -e "  Workers Archivos: ${GREEN}$workers_files${NC}"
echo -e "  Utils Archivos: ${GREEN}$utils_files${NC}"
echo ""

# TOTAL STATS
echo "=================================================="
echo -e "${YELLOW}📊 ESTADÍSTICAS${NC}"
echo "=================================================="

total_files=$((domain_files + data_files + presentation_files + di_files + workers_files + utils_files))
echo -e "Total de archivos .kt creados: ${GREEN}$total_files${NC}"
echo ""

# Listar archivos creados
echo -e "${YELLOW}📄 Archivos Creados:${NC}"
echo ""

echo "DOMAIN:"
find "$BASE_PATH/domain" -type f -name "*.kt" 2>/dev/null | sed 's|.*domain/||' | sed 's/^/  ✓ /'

echo ""
echo "DATA:"
find "$BASE_PATH/data" -type f -name "*.kt" 2>/dev/null | sed 's|.*data/||' | sed 's/^/  ✓ /'

echo ""
echo "PRESENTATION:"
find "$BASE_PATH/presentation" -type f -name "*.kt" 2>/dev/null | sed 's|.*presentation/||' | sed 's/^/  ✓ /' || echo "  (Por crear)"

echo ""
echo "DI:"
find "$BASE_PATH/di" -type f -name "*.kt" 2>/dev/null | sed 's|.*di/||' | sed 's/^/  ✓ /'

echo ""
echo "OTHERS:"
find "$BASE_PATH/workers" -type f -name "*.kt" 2>/dev/null | sed 's|.*workers/||' | sed 's/^/  ✓ /' || echo "  (Por crear)"
find "$BASE_PATH/utils" -type f -name "*.kt" 2>/dev/null | sed 's|.*utils/||' | sed 's/^/  ✓ /'

echo ""
echo "=================================================="
echo -e "${GREEN}✅ Verificación completada${NC}"
echo "=================================================="
echo ""
echo "📖 Para más información, ver:"
echo "  - ARCHITECTURE.md (Conceptos)"
echo "  - TREE.md (Estructura)"
echo "  - ONBOARDING.md (Guía de inicio)"
echo ""

